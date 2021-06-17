
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

class DealerTestCase extends GroovyTestCase {
    def numCards = 60
    def dealer = new Dealer()

    def players = []

    void setUp() {
        def strategy = new PopCard()
        players << new Player('Phil H', strategy, numCards)
        players << new Player('Daniel N', strategy, numCards)
        players << new Player('Doyle B', strategy, numCards)
    }

    void testPlayRound_Default() {
        def prizeCard = 42

        players[0].hand = [10,11,12]
        players[1].hand = [15,16,17]
        players[2].hand = [58,50,49]

        // test
        def winner = dealer.playRound(prizeCard, players)

        assert 'Doyle B' == winner.name
        assert 1 == winner.playerStats.numRoundsWon
        assert prizeCard == winner.playerStats.total
    }

    void testPlay_Default() {
        def kitty = [35,25,55]

        players[0].hand = [10,11,52]
        players[1].hand = [15,16,17]
        players[2].hand = [58,50,49]

        def table = new Table(players, kitty)

        // test
        def winner = dealer.play(table)

        assert 0 == table.players[0].hand.size()
        assert 0 == table.players[1].hand.size()
        assert 0 == table.players[2].hand.size()
    }

    void testNewDeckSize() {
        // test
	    def deck = dealer.newDeck(numCards)

	    assert numCards == deck.size()
	}

    void testNewDeckComplete() {
        // test
	    def deck = dealer.newDeck(numCards)

	    def uniques = new HashSet()
	    deck.each { uniques << it }
	    assert numCards == uniques.size()
	}

    void testNewDeckShuffled() {
        // test
	    def deck = dealer.newDeck(numCards)

        def numMatches = 0
        (1..numCards).each { i ->
            if (i == deck[i-1]) {
                numMatches++
            }
        }
        // if more than 50% of cards remain in original slot, something is weird
        def maxMatches = (numCards / 2) as int
        assert numMatches < maxMatches
    }

	void testGetNumCardsInHand() {
	    assert 15 == dealer.getNumCardsInHand(numCards, 3)
	}

    void testDeal() {
        def numCardsInHand = dealer.getNumCardsInHand(numCards, 3)

        // test
        def table = dealer.deal(numCards, players)

        assert numCardsInHand == table.kitty.size()
        assert 3 == table.players.size()
        for (p in players) { assert numCardsInHand == p.hand.size() }
    }
}
