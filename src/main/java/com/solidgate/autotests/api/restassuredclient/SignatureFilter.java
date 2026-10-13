package com.solidgate.autotests.api.restassuredclient;

import io.restassured.filter.*;
import io.restassured.response.*;
import io.restassured.specification.*;

import static com.solidgate.autotests.config.SolidgateConfig.*;
import static com.solidgate.autotests.utils.api.RequestSignatureUtils.*;

public class SignatureFilter implements Filter {

    static final String MERCHANT_HEADER = "merchant";
    static final String SIGNATURE_HEADER = "signature";

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
                           FilterContext context) {
        var solidgateConfig = current();
        requestSpec.header(MERCHANT_HEADER, solidgateConfig.publicKey());

        var requestBody = requestSpec.<String>getBody();
        var signature = signRequestBody(solidgateConfig.publicKey(), solidgateConfig.secretKey(), requestBody);
        requestSpec.header(SIGNATURE_HEADER, signature);

        return context.next(requestSpec, responseSpec);
    }
}
