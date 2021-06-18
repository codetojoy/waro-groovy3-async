
package net.codetojoy.waro.util

import groovy.transform.NullCheck

@NullCheck
class Logger {
    final boolean verbose

    Logger(boolean verbose) {
        this.verbose = verbose
    }

    def log(Closure c) {
        if (verbose) {
            c.call()
        }
    }
}