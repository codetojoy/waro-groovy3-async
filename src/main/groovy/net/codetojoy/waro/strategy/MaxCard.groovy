
package net.codetojoy.waro.strategy

import groovy.transform.NullCheck

@NullCheck
class MaxCard implements Strategy {
    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        hand.max()
    }
}
