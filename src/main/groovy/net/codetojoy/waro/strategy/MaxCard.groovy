
package net.codetojoy.waro.strategy

class MaxCard implements Strategy {
    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        hand.max()
    }
}
