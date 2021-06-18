
package net.codetojoy.waro.strategy

class RandomCardTestCase extends GroovyTestCase {
    
    def numCards = 60
            
    void testRandomStrategy() {
        def card = 10 
        def hand = [1, 60, 11, 40, 19]
        def strategy = new RandomCard()

        // test
        def result = strategy.selectCard(card, hand, numCards)

        assert hand.contains(result)
    }
}
