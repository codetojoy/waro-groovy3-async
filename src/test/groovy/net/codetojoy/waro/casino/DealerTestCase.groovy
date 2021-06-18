
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

    void testPlay_Default() {
        def kitty = [1,2,3]
        def numCards = 12

        players[0].hand = [4,9,10]
        players[1].hand = [5,8,12]
        players[2].hand = [6,7,11]

        def table = new Table(players, kitty, numCards)

        // test
        def winner = dealer.play(table)

        table.players.each { assert it.hand.isEmpty() }
        assert 2 == players[0].playerStats.total
        assert 3 == players[1].playerStats.total
        assert 1 == players[2].playerStats.total
        table.players.each { assert 1 == it.playerStats.numRoundsWon }
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
