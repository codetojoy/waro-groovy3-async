package net.codetojoy.waro.strategy.api

import java.net.URI

import org.apache.http.client.utils.URIBuilder

import groovy.transform.NullCheck

@NullCheck
class ApiURI {
    private static final String CARDS_PARAM = "cards"
    private static final String MODE_PARAM = "mode"
    private static final String PRIZE_CARD_PARAM = "prize_card"
    private static final String MAX_CARD_PARAM = "max_card"

    def scheme
    def host
    def path
    def mode

    ApiURI(String scheme, String host, String path, String mode) {
        this.scheme = scheme
        this.host = host
        this.path = path
        this.mode = mode
    }

    URI buildURI(int prizeCard, List<Integer> hand, int maxCard) {
        URIBuilder builder = new URIBuilder()

        builder.setScheme(scheme)
               .setHost(host)
               .setPath(path)
               .setParameter(MODE_PARAM, mode)
               .setParameter(PRIZE_CARD_PARAM, "" + prizeCard)
               .setParameter(MAX_CARD_PARAM, "" + maxCard)

        def cardsStrings = hand.collect { c -> "" + c }

        def cardsQueryValue = String.join(",", cardsStrings)

        builder.setParameter(CARDS_PARAM, cardsQueryValue)

        URI uri = builder.build()

        return uri
    }
}