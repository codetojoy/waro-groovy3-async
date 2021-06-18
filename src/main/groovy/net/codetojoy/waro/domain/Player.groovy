
package net.codetojoy.waro.domain

import groovy.transform.NullCheck

import net.codetojoy.waro.strategy.*

@NullCheck
class Player {
    final String name
    final Strategy strategy
    final PlayerStats playerStats = new PlayerStats()
    Integer maxCard
    List<Integer> hand = []

    Player(String name, Strategy strategy) {
        this.name = name
        this.strategy = strategy
    }

    Player(String name, Strategy strategy, Integer maxCard) {
        this.name = name
        this.strategy = strategy
        this.maxCard = maxCard
    }

    void applyBid(int card) {
        assert hand.contains(card)
        hand.remove(card as Object)
    }

    void clear() {
        hand = []
        playerStats.clear()
    }

    StrategyExecutor getStrategy(int prizeCard) {
        new StrategyExecutor(strategy, prizeCard, hand, maxCard, this)
    }
}
