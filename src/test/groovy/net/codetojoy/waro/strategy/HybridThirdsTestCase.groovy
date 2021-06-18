
package net.codetojoy.waro.strategy

class HybridThirdsTestCase extends GroovyTestCase {
    def numCards = 60
    def lowStrategy = new MinCard()
    def midStrategy = new PopCard()
    def topStrategy = new MaxCard()
    def strategy
    def hand

    void setUp() {
        hand = [20, 1, 60, 11, 40, 19]
        strategy = new HybridThirds(topStrategy, midStrategy, lowStrategy)
    }

    void testLowStrategy() {
        def prizeCard = 10

        // test
        def result = strategy.selectCard(prizeCard, hand, numCards)

        def expected = lowStrategy.selectCard(prizeCard, hand, numCards)
        assert result == expected
    }

    void testMidStrategy() {
        def prizeCard = 30

        // test
        def result = strategy.selectCard(prizeCard, hand, numCards)

        def expected = midStrategy.selectCard(prizeCard, hand, numCards)
        assert result == expected
    }

    void testTopStrategy() {
        def prizeCard = 50

        // test
        def result = strategy.selectCard(prizeCard, hand, numCards)

        def expected = topStrategy.selectCard(prizeCard, hand, numCards)
        assert result == expected
    }
}
