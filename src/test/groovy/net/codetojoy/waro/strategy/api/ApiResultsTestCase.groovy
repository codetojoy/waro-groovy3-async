
package net.codetojoy.waro.strategy.api

class ApiResultTestCase extends GroovyTestCase {

    void testfromJson() {
        def json = """
{"card": 5150, "message": "Van Halen"}
"""

        // test
        def result = new ApiResults().fromJson(json)

        assert 5150 == result.card
        assert 'Van Halen' == result.message
    }
}
