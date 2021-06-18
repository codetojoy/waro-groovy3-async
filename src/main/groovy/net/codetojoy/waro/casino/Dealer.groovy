
package net.codetojoy.waro.casino

import java.util.function.Supplier

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.domain.async.BidFetcher
import net.codetojoy.waro.util.Logger

import com.google.common.collect.Lists

class Dealer {

    def verbose = true
    def logger = new Logger(verbose)

    Table deal(int numCards, def players) {
        def numPlayers = players.size()

        def hands = dealHands(numCards, numPlayers)

        def kitty = hands[0]

        for (index in 1..numPlayers) {
            players[index - 1].hand = hands[index]
        }

        Table table = new Table(players, kitty)

        return table
    }

    void play(Table table) {
        table.kitty.each { prizeCard ->
            playRound(prizeCard, table.players)
        }
    }

    // -- internal

    protected def dealHands(int numCards, int numPlayers) {
        def hands = []

        def deck = newDeck(numCards)
        def numCardsInHand = getNumCardsInHand(numCards, numPlayers)

        Lists.partition(deck, numCardsInHand).each { unmodifiableHand ->
            def hand = []
            hand.addAll(unmodifiableHand)
            hands << hand
        }

        return hands
    }

    protected def playRound(def prizeCard, def players) {
        def winningBid = findRoundWinner(prizeCard, players)
        def winner = winningBid.player

        logger.log({ println "\nthis round: ${winner.name} WINS $prizeCard with ${winningBid.offer}" })

        winner.playerStats.numRoundsWon++
        winner.playerStats.total += prizeCard

        winner
    }

    // returns Expando with 'Player winner' and 'int winningBid'
    protected def findRoundWinner(def prizeCard, def players) {
        def bids = getAllBids(prizeCard, players)
        bids.each { bid -> bid.player.applyBid(bid.offer) }
        def winningBid = bids.max { b -> b.offer }

        return winningBid
    }

    protected def getAllBids(def prizeCard, def players) {
        List<Supplier<Bid>> tasks = players.collect(p -> p.getStrategy(prizeCard))

        def bidFetcher = new BidFetcher()
        def bids = bidFetcher.fetchBids(tasks)
        bids
    }

    protected def newDeck(def numCards) {
        def deck = []

        (1..numCards).each { deck << it }

        deck.shuffle()

        return deck
    }

    protected def getNumCardsInHand(def numCards, def numPlayers) {
        return (numCards / (numPlayers + 1)) as int
    }
}
