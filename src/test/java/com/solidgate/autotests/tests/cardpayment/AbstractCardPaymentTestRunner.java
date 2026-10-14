package com.solidgate.autotests.tests.cardpayment;

import com.solidgate.autotests.api.controllers.cardpayments.*;
import com.solidgate.autotests.api.controllers.paymentpage.*;
import com.solidgate.autotests.exceptions.*;
import com.solidgate.autotests.models.api.paymentpage.requests.*;
import com.solidgate.autotests.models.api.paymentpage.responses.*;
import com.solidgate.autotests.models.entities.*;
import com.solidgate.autotests.pages.paymentpage.*;
import com.solidgate.autotests.tests.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;

import static java.util.Objects.*;

@Slf4j
public abstract class AbstractCardPaymentTestRunner extends AbstractTestRunner {

    private static final PaymentPageController PAYMENT_PAGE_CONTROLLER = new PaymentPageController();
    protected static final CardPaymentsController CARD_PAYMENTS_CONTROLLER = new CardPaymentsController();
    private String paymentPageId;

    protected PaymentPage createOrderAndOpenPaymentPage(InitPaymentPageRequestDto initPaymentPageRequestDto) {
        var initResponseDto = PAYMENT_PAGE_CONTROLLER
                .initPaymentPage(initPaymentPageRequestDto)
                .getBodyAs(InitPaymentPageResponseDto.class);
        paymentPageId = initResponseDto.getId();

        return new PaymentPage().openPage(initResponseDto.getUrl());
    }

    protected PaymentStatusPage payWithCard(PaymentPage paymentPage, CardEntity card) {
        return paymentPage
                .setCardNumber(card.getNumber())
                .setCardExpiryDate(card.getExpirationDate())
                .setCardCvv(card.getCvv())
                .submitPayment()
                .waitTillPaymentProcessed();
    }

    @AfterEach
    void deactivateCreatedPaymentPage() {
        if (isNull(paymentPageId)) {
            return;
        }

        try {
            PAYMENT_PAGE_CONTROLLER.deactivatePaymentPage(paymentPageId);
            log.info("Payment page {} deactivated", paymentPageId);
        } catch (FrameworkException alreadyClosed) {
            log.warn("Skip deactivation of payment page {} - it is already paid or closed: {}", paymentPageId,
                    alreadyClosed.getMessage());
        }
    }
}
