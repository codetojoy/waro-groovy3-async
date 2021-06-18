
package net.codetojoy.waro.strategy

class ApiRemoteTestCase extends GroovyTestCase {

    void testfromJson() {
        def SCHEME = "http"
        def HOST = "localhost:8080"
        def PATH = "waro/strategy"
        def MODE = "max"
        def json = """
{"card": 5150, "message": "Van Halen"}
"""
        // test
        def result = new ApiRemote(SCHEME, HOST, PATH, MODE).getCard(json)

        assert 5150 == result
    }
}
