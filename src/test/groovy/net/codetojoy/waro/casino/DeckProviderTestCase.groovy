
package net.codetojoy.waro.casino

class DeckProviderTestCase extends GroovyTestCase {
    def numCards = 60
    def deckProvider = new DeckProvider(numCards)

    void testNewDeckSize() {
        // test
	    def deck = deckProvider.newDeck()

	    assert numCards == deck.size()
	}

    void testNewDeckComplete() {
        // test
	    def deck = deckProvider.newDeck()

	    def uniques = new HashSet()
	    deck.each { uniques << it }
	    assert numCards == uniques.size()
	}

    void testNewDeckShuffled() {
        // test
	    def deck = deckProvider.newDeck()

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
}
