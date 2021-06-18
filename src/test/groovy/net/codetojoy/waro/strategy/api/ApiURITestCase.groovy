
package net.codetojoy.waro.strategy.api

import java.net.URLEncoder

class ApiURITestCase extends GroovyTestCase {

    void testfromJson() {
        def SCHEME = "http"
        def HOST = "localhost:8080"
        def PATH = "waro/strategy"
        def MODE = "max"
        def prizeCard = 18
        def hand = [10,20,30]
        def maxCard = 40
        def apiURI = new ApiURI(SCHEME, HOST, PATH, MODE)

        // test
        def result = apiURI.buildURI(prizeCard, hand, maxCard).toString()

        String expected = "http://localhost:8080/waro/strategy?mode=max&prize_card=18&max_card=40&cards=10%2C20%2C30"
        assert expected == (result as String)
    }
}
