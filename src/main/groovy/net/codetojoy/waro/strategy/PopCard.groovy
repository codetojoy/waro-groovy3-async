
package net.codetojoy.waro.strategy

import groovy.transform.NullCheck

@NullCheck
class PopCard implements Strategy {
    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        hand[0]
    }
}
