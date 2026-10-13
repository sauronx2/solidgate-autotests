package com.solidgate.autotests.api.clients;

import com.solidgate.autotests.api.restassuredclient.*;
import io.restassured.filter.log.*;

import java.net.*;

import static com.solidgate.autotests.api.restassuredclient.RestClientFactory.*;

public abstract class AbstractSolidgateClient {

    protected RestClientWrapper getSignedAndLoggedClient(URI baseUri) {
        return getClient(baseUri)
                .addFilter(new SignatureFilter())
                .addFilter(getCredentialsHiddenRequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());
    }
}
