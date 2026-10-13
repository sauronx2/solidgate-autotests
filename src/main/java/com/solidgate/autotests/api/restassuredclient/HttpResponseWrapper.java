package com.solidgate.autotests.api.restassuredclient;

import com.solidgate.autotests.exceptions.*;
import io.restassured.response.*;

public record HttpResponseWrapper(ValidatableResponse response) {

    private static final String UNEXPECTED_STATUS_CODE_MESSAGE = "Expecting %s status code but was %s";

    public HttpResponseWrapper expectStatusCode(int statusCode) {
        try {
            response.statusCode(statusCode);
        } catch (AssertionError error) {
            throw new FrameworkException(UNEXPECTED_STATUS_CODE_MESSAGE.formatted(statusCode, getStatusCode()), error);
        }

        return this;
    }

    public <T> T getBodyAs(Class<T> bodyType) {
        return response
                .extract()
                .body()
                .as(bodyType);
    }

    private int getStatusCode() {
        return response
                .extract()
                .statusCode();
    }
}
