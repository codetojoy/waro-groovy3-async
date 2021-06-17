
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.util.Logger

class Game {
    def verbose = true
    def logger = new Logger(verbose)

    def playGame(def numCards, def players) {
        def dealer = new Dealer()

        def table = dealer.deal(numCards, players)

        logger.log({
            table.players.each { p -> println "${p.name}: ${p.hand.toString()}" }
        })

        dealer.play(table)

        determineWinner(table)
    }

    // ---- internal

    protected def determineWinner(def table) {
        def kitty = table.kitty
        def players = table.players

        table.assertTotals()

        def winner = players.max { p -> p.playerStats.total }
        def max = winner.playerStats.total

        logger.log({
            players.each { p ->
                def stats = p.playerStats
                println "$p.name won $stats.numRoundsWon rounds with $stats.total"
            }
        })

        winner.playerStats.numGamesWon++
        println "\nGame summary:"
        println "overall WINNER is: $winner.name "
    }
}
