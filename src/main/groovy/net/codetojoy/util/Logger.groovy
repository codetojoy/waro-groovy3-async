
package net.codetojoy.waro.util

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