
package net.codetojoy.waro.domain

import groovy.transform.NullCheck

@NullCheck
class Bid {
    final Integer offer
    final Player player

    Bid(Integer offer, Player player) {
        this.offer = offer
        this.player = player
    }
}
