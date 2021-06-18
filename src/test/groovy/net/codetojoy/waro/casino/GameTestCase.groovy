package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

// Mockito has been a pain, so rolling my own
class MockDeckProvider extends DeckProvider {
    def fixedDeck

    @Override
    def newDeck() {
        return fixedDeck
    }
}

class GameTestCase extends GroovyTestCase {
    def numCards = 12
    def players = []
    def game = new Game()

    void setUp() {
        def strategy = new PopCard()
        players << new Player('mozart', strategy, numCards)
        players << new Player('chopin', strategy, numCards)
        players << new Player('bach', strategy, numCards)
    }

    void testPlay_Default() {
        // kitty = [1,2,3]
        // players[0].hand = [4,9,10]
        // players[1].hand = [5,8,12]
        // players[2].hand = [6,7,11]
        def stackedDeck = [1,2,3,4,9,10,5,8,12,6,7,11]
        def mockDeckProvider = new MockDeckProvider()
        mockDeckProvider.fixedDeck = stackedDeck

        // test
        def winner = game.playGame(numCards, players, mockDeckProvider)

        players.each { assert it.hand.isEmpty() }
        players.each { assert 1 == it.playerStats.numRoundsWon }
        assert 2 == players[0].playerStats.total
        assert 3 == players[1].playerStats.total
        assert 1 == players[2].playerStats.total
        assert 0 == players[0].playerStats.numGamesWon
        assert 1 == players[1].playerStats.numGamesWon
        assert 0 == players[2].playerStats.numGamesWon
    }
}
