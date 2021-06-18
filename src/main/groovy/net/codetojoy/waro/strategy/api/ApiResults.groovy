package net.codetojoy.waro.strategy.api

import com.fasterxml.jackson.databind.ObjectMapper

import groovy.transform.NullCheck

@NullCheck
class ApiResults {
    ApiResult fromJson(String str) {
        ObjectMapper mapper = new ObjectMapper()
        ApiResult result = mapper.readValue(str, ApiResult.class)
        return result
    }
}