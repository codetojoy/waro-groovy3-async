package net.codetojoy.waro.strategy

import java.util.function.Supplier
import java.util.stream.IntStream

import net.codetojoy.waro.domain.*

class StrategyExecutor implements Supplier<Bid> {
    private final Strategy strategy
    private final int prizeCard
    private final IntStream hand
    private final int maxCard
    private final Player bidder

    public StrategyExecutor(Strategy strategy, int prizeCard, IntStream hand, int maxCard, Player player) {
        this.strategy = strategy
        this.prizeCard = prizeCard
        this.hand = hand
        this.maxCard = maxCard
        this.bidder = player
    }

    @Override
    public Bid get() {
        int offer = strategy.selectCard(prizeCard, hand, maxCard)
        new Bid(prizeCard, offer, bidder)
    }
}