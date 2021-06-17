
package net.codetojoy.waro

import net.codetojoy.waro.casino.Tourney

// TODO: add logging

try {
    def config = new Config(args[0])

    def tourney = new Tourney(config)
    tourney.playGames()
} catch (Exception e) {
   System.err.println "internal error: $e.message"
}









