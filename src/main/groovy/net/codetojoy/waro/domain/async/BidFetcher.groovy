
package net.codetojoy.waro.domain.async

import java.util.concurrent.*
import java.util.function.*
import java.util.stream.*

import net.codetojoy.waro.domain.Bid

import groovy.transform.NullCheck

@NullCheck
class BidFetcher {

    protected List<Bid> internalFetchBids(List<Supplier<Bid>> tasks) throws Exception {
        List<CompletableFuture<Bid>> futures = []

        tasks.each { task ->
            def f = CompletableFuture.supplyAsync(task)
            futures << f
        }

        def futuresArray = futures.toArray(new CompletableFuture[futures.size()])

        def allFutures = CompletableFuture.allOf(futuresArray)

        // CompletableFuture<List<Bid>>
        def compoundFuture = allFutures.thenApply(v -> {
            return futures.stream()
                          .map(f -> f.join())
                          .collect(Collectors.toList())
        })

        def results = compoundFuture.get()
        return results
    }

    List<Bid> fetchBids(List<Supplier<Bid>> tasks) {
        List<Bid> bids = null

        try {
            bids = internalFetchBids(tasks)
        } catch (Exception ex) {
            System.err.println("ERROR: caught exception: " + ex.getMessage())
            // just bail out ¯\_(ツ)_/¯
            System.exit(-1)
        }

        return bids
    }
}
