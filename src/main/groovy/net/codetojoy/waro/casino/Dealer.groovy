
package net.codetojoy.waro.casino

import java.util.function.Supplier

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.domain.async.BidFetcher
import net.codetojoy.waro.util.Logger

import com.google.common.collect.Lists

import groovy.transform.NullCheck

@NullCheck
class Dealer {
    def verbose = true
    def logger = new Logger(verbose)
    def deckProvider

    Dealer(DeckProvider deckProvider) {
        this.deckProvider = deckProvider
    }

    Table deal(int numCards, def players) {
        def numPlayers = players.size()

        def hands = dealHands(numCards, numPlayers)

        def kitty = hands[0]

        for (index in 1..numPlayers) {
            players[index - 1].hand = hands[index]
        }

        Table table = new Table(players, kitty, numCards)

        return table
    }

    void play(Table table) {
        table.kitty.each { prizeCard ->
            playRound(prizeCard, table)
        }
    }

    // -- internal

    protected def dealHands(int numCards, int numPlayers) {
        def hands = []

        def deck = deckProvider.newDeck()
        def numCardsInHand = getNumCardsInHand(numCards, numPlayers)

        Lists.partition(deck, numCardsInHand).each { unmodifiableHand ->
            def hand = []
            hand.addAll(unmodifiableHand)
            hands << hand
        }

        return hands
    }

    protected def playRound(def prizeCard, def table) {
        def winningBid = findRoundWinner(prizeCard, table)
        def winner = winningBid.player

        logger.log({ println "\nthis round: ${winner.name} WINS $prizeCard with ${winningBid.offer}" })

        winner.playerStats.numRoundsWon++
        winner.playerStats.total += prizeCard

        table.assertAudit()

        winner
    }

    // returns Expando with 'Player winner' and 'int winningBid'
    protected def findRoundWinner(def prizeCard, def table) {
        def bids = getAllBids(prizeCard, table.players)
        bids.each { bid ->
            def offer = bid.offer
            bid.player.applyBid(offer)
            table.discard(offer)
        }
        def winningBid = bids.max { b -> b.offer }

        return winningBid
    }

    protected def getAllBids(def prizeCard, def players) {
        List<Supplier<Bid>> tasks = players.collect(p -> p.getStrategy(prizeCard))

        def bidFetcher = new BidFetcher()
        def bids = bidFetcher.fetchBids(tasks)
        bids
    }

    protected def getNumCardsInHand(def numCards, def numPlayers) {
        return (numCards / (numPlayers + 1)) as int
    }
}
