
package net.codetojoy.waro.strategy

import groovy.transform.NullCheck

@NullCheck
class MinCard implements Strategy {
    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        hand.min()
    }
}
