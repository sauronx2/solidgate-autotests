package com.solidgate.autotests.api.clients.paymentpage;

import com.solidgate.autotests.api.clients.*;
import com.solidgate.autotests.api.restassuredclient.*;
import com.solidgate.autotests.models.api.paymentpage.requests.*;
import lombok.*;

import static com.solidgate.autotests.api.clients.paymentpage.PaymentPageClient.PaymentPageResource.*;
import static com.solidgate.autotests.config.SolidgateConfig.*;
import static com.solidgate.autotests.utils.api.JsonUtils.*;
import static lombok.AccessLevel.*;

public class PaymentPageClient extends AbstractSolidgateClient {

    public HttpResponseWrapper sendInitPaymentPageRequest(InitPaymentPageRequestDto requestDto) {
        var baseUri = current().paymentPageUrl();

        return getSignedAndLoggedClient(baseUri)
                .body(toJson(requestDto))
                .post(INIT_PAYMENT_PAGE_URL);
    }

    @NoArgsConstructor(access = PRIVATE)
    static class PaymentPageResource {
        static final String INIT_PAYMENT_PAGE_URL = "/init";
    }
}
