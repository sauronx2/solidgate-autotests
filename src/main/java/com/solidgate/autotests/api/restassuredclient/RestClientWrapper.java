package com.solidgate.autotests.api.restassuredclient;

import io.restassured.filter.*;
import io.restassured.specification.*;
import lombok.*;

@RequiredArgsConstructor
public class RestClientWrapper {

    private final RequestSpecification requestSpecification;

    public HttpResponseWrapper post(String path) {
        var response = requestSpecification
                .post(path)
                .then();

        return new HttpResponseWrapper(response);
    }

    public RestClientWrapper body(Object body) {
        requestSpecification.body(body);

        return this;
    }

    public RestClientWrapper addFilter(Filter filter) {
        requestSpecification.filter(filter);

        return this;
    }
}
