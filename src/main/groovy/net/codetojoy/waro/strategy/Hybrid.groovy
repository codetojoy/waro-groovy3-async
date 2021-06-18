
package net.codetojoy.waro.strategy

import groovy.transform.NullCheck

@NullCheck
class Hybrid implements Strategy {
    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        def result = hand.min()

        if (prizeCard > (numCards/2)) {
            result = hand.max()
        }

        result
    }
}
