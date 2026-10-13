package com.solidgate.autotests.api.restassuredclient;

import io.restassured.config.*;
import io.restassured.filter.log.*;
import lombok.experimental.*;

import java.net.*;
import java.util.*;

import static com.solidgate.autotests.api.restassuredclient.SignatureFilter.*;
import static com.solidgate.autotests.utils.api.JsonUtils.*;
import static io.restassured.RestAssured.*;
import static io.restassured.config.ObjectMapperConfig.*;
import static io.restassured.config.RestAssuredConfig.*;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.mapper.ObjectMapperType.*;
import static java.lang.System.*;

@UtilityClass
public class RestClientFactory {

    public static RestClientWrapper getClient(URI baseUri) {
        var requestSpecification = given()
                .baseUri(baseUri.toString())
                .contentType(JSON)
                .config(buildRestAssuredConfig());

        return new RestClientWrapper(requestSpecification);
    }

    public static RequestLoggingFilter getCredentialsHiddenRequestLoggingFilter() {
        var hiddenHeaders = Set.of(MERCHANT_HEADER, SIGNATURE_HEADER);

        return new RequestLoggingFilter(ALL, true, out, true, hiddenHeaders);
    }

    private static RestAssuredConfig buildRestAssuredConfig() {
        var objectMapperConfig = objectMapperConfig()
                .defaultObjectMapperType(JACKSON_3)
                .jackson3ObjectMapperFactory((type, charset) -> getJsonMapper());

        return newConfig().objectMapperConfig(objectMapperConfig);
    }
}
