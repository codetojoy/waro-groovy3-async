
package net.codetojoy.waro.strategy

import groovy.transform.NullCheck

@NullCheck
class NearestCard implements Strategy {
    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        hand.min { Math.abs(prizeCard - it) }
    }
}
