
package net.codetojoy.waro.strategy

import groovy.transform.NullCheck

@NullCheck
class RandomCard implements Strategy {
    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        def index = new Random().nextInt(hand.size())
        hand[index]
    }
}
