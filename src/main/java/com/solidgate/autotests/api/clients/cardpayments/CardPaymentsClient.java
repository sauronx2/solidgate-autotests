package com.solidgate.autotests.api.clients.cardpayments;

import com.solidgate.autotests.api.clients.*;
import com.solidgate.autotests.api.restassuredclient.*;
import com.solidgate.autotests.models.api.cardpayments.requests.*;
import lombok.*;

import static com.solidgate.autotests.api.clients.cardpayments.CardPaymentsClient.CardPaymentsResource.*;
import static com.solidgate.autotests.config.SolidgateConfig.*;
import static com.solidgate.autotests.utils.api.JsonUtils.*;
import static lombok.AccessLevel.*;

public class CardPaymentsClient extends AbstractSolidgateClient {

    public HttpResponseWrapper sendOrderStatusRequest(OrderStatusRequestDto requestDto) {
        var baseUri = current().gateUrl();

        return getSignedAndLoggedClient(baseUri)
                .body(toJson(requestDto))
                .post(ORDER_STATUS_URL);
    }

    @NoArgsConstructor(access = PRIVATE)
    static class CardPaymentsResource {
        static final String ORDER_STATUS_URL = "/status";
    }
}
