
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.Config

import groovy.transform.NullCheck

@NullCheck
class Tourney {
    final List<Player> players = []
    final int numGames
    final int numCards

    Tourney(Config config) {
        players = config.players
        numGames = config.numGames
        numCards = config.numCards
    }

    void playGames() {
        numGames.times {
            playGame(numCards)
        }

        println "\nTourney summary:  "

        players.each { p ->
            println "${p.name} has ${p.playerStats.numGamesWon} wins over ${numGames} games"
        }
    }

    // ------- internal

    protected void playGame(int numCards) {
        def deckProvider = new DeckProvider(numCards)
        def game = new Game()
        // game.verbose = false
        game.playGame(numCards, players, deckProvider)
        players.each { p -> p.clear() }
    }

}
