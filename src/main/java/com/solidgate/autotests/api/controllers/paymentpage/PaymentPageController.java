package com.solidgate.autotests.api.controllers.paymentpage;

import com.solidgate.autotests.api.clients.paymentpage.*;
import com.solidgate.autotests.api.restassuredclient.*;
import com.solidgate.autotests.exceptions.*;
import com.solidgate.autotests.models.api.paymentpage.requests.*;
import com.solidgate.autotests.models.api.paymentpage.responses.*;

import static java.net.HttpURLConnection.*;
import static java.util.Objects.*;

public class PaymentPageController {

    private final PaymentPageClient paymentPageClient = new PaymentPageClient();

    public HttpResponseWrapper initPaymentPage(InitPaymentPageRequestDto requestDto) {
        var initResponse = paymentPageClient
                .sendInitPaymentPageRequest(requestDto)
                .expectStatusCode(HTTP_OK);
        var initError = initResponse
                .getBodyAs(InitPaymentPageResponseDto.class)
                .getError();
        if (nonNull(initError)) {
            throw new FrameworkException("Payment page init request failed: " + initError);
        }

        return initResponse;
    }
}
