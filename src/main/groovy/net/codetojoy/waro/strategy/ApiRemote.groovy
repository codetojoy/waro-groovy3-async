package net.codetojoy.waro.strategy

import net.codetojoy.waro.strategy.api.*

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import groovy.transform.NullCheck

@NullCheck
class ApiRemote implements Strategy {
    def apiURI

    ApiRemote(String scheme, String host, String path, String mode) {
        this.apiURI = new ApiURI(scheme, host, path, mode)
    }

    @Override
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        var bid = 0

        try {
             bid = apiRemoteSelectCard(prizeCard, hand, maxCard)
        } catch (Exception ex) {
            System.err.println("ERROR caught exception: ${ex.message}")
            // bail out for now
            System.exit(-1)
        }

        return bid
    }

    protected int getCard(String remoteResult) {
        ApiResult apiResult = new ApiResults().fromJson(remoteResult)
        apiResult.card
    }

    private int apiRemoteSelectCard(int prizeCard, List<Integer> hand, int maxCard) throws Exception {
        var card = 0
        var uri = apiURI.buildURI(prizeCard, hand, maxCard)

        def request = new HttpGet(uri)

        try (def httpClient = HttpClients.createDefault()
             def response = httpClient.execute(request)) {

            def entity = response.entity
            if (entity) {
                String resultStr = EntityUtils.toString(entity)
                println("TRACER api remote: " + resultStr)
                card = getCard(resultStr)
            }
        }

        return card
    }

}