package net.codetojoy.waro.strategy.api

class ApiResult {
    int card
    String message

    String toString() {
        return "card: " + card + " message: " + message
    }
}
