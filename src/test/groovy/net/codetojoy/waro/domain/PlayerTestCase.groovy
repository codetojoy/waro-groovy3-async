
package net.codetojoy.waro.domain

import net.codetojoy.waro.strategy.*

class PlayerTestCase extends GroovyTestCase {

    def strategy = new PopCard()
    def player = new Player('Peterborough Pete', strategy, 60)

    void testApplyBid() {
        player.hand = [10,20,30]
        def bid = new Bid(20, player)

        // test
        player.applyBid(bid.offer)

        assert 2 == player.hand.size()
    }
}
