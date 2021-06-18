
package net.codetojoy.waro.domain

import groovy.transform.NullCheck

@NullCheck
class Table {
    final List<Player> players
    final List<Integer> kitty
    List<Integer> discardedCards = []
    int numCards

    Table(List<Player> players, List<Integer> kitty, int numCards) {
        this.players = players
        this.kitty = kitty
        this.numCards = numCards
    }

    void discard(int card) {
        discardedCards << card
    }

    void assertAudit() {
        def total = (numCards * (numCards + 1)) / 2 as int
        def actualTotal = kitty.sum()
        def playersSum = players.each { p ->
            actualTotal += (p.hand.isEmpty()) ? 0 : p.hand.sum()
        }
        actualTotal += discardedCards.sum()
        assert total == actualTotal
    }

    void assertTotals() {
        def playerTotal = 0
        def roundsTotal = 0

        players.each { p ->
            playerTotal += p.playerStats.total
            roundsTotal += p.playerStats.numRoundsWon
        }

        assert kitty.sum() == playerTotal
        assert kitty.size() == roundsTotal
    }
}
