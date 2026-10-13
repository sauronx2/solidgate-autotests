package com.solidgate.autotests.api.controllers.cardpayments;

import com.solidgate.autotests.api.clients.cardpayments.*;
import com.solidgate.autotests.exceptions.*;
import com.solidgate.autotests.models.api.cardpayments.requests.*;
import com.solidgate.autotests.models.api.cardpayments.responses.*;

import java.time.*;

import static java.net.HttpURLConnection.*;
import static java.time.Duration.*;
import static java.util.Objects.*;
import static org.awaitility.Awaitility.*;

public class CardPaymentsController {

    private static final Duration TERMINAL_ORDER_STATUS_TIMEOUT = ofSeconds(60);
    private static final Duration ORDER_STATUS_POLL_INTERVAL = ofSeconds(2);
    private final CardPaymentsClient cardPaymentsClient = new CardPaymentsClient();

    public OrderStatusResponseDto waitForTerminalOrderStatus(String orderId) {
        var orderStatusRequestDto = new OrderStatusRequestDto(orderId);

        return await()
                .alias("Order %s should leave its non-terminal statuses".formatted(orderId))
                .atMost(TERMINAL_ORDER_STATUS_TIMEOUT)
                .pollInterval(ORDER_STATUS_POLL_INTERVAL)
                .ignoreException(FrameworkException.class)
                .until(() -> requestOrderStatus(orderStatusRequestDto), this::hasTerminalOrderStatus);
    }

    private OrderStatusResponseDto requestOrderStatus(OrderStatusRequestDto orderStatusRequestDto) {
        var responseDto = cardPaymentsClient
                .sendOrderStatusRequest(orderStatusRequestDto)
                .expectStatusCode(HTTP_OK)
                .getBodyAs(OrderStatusResponseDto.class);
        if (isNull(responseDto.getOrder())) {
            throw new FrameworkException("Order status request failed: " + responseDto.getError());
        }

        return responseDto;
    }

    private boolean hasTerminalOrderStatus(OrderStatusResponseDto responseDto) {
        return responseDto
                .getOrder()
                .getStatus()
                .isTerminal();
    }
}
