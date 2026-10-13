package com.solidgate.autotests.tests.cardpayment;

import com.solidgate.autotests.api.controllers.cardpayments.*;
import com.solidgate.autotests.api.controllers.paymentpage.*;
import com.solidgate.autotests.models.api.paymentpage.requests.*;
import com.solidgate.autotests.models.api.paymentpage.responses.*;
import com.solidgate.autotests.models.entities.*;
import com.solidgate.autotests.pages.paymentpage.*;
import com.solidgate.autotests.tests.*;

public abstract class AbstractCardPaymentTestRunner extends AbstractTestRunner {

    private static final PaymentPageController PAYMENT_PAGE_CONTROLLER = new PaymentPageController();
    protected static final CardPaymentsController CARD_PAYMENTS_CONTROLLER = new CardPaymentsController();
    protected InitPaymentPageRequestDto initPaymentPageRequestDto;
    protected CardEntity card;

    protected PaymentPage createOrderAndOpenPaymentPage(InitPaymentPageRequestDto initPaymentPageRequestDto) {
        var pageUrl = PAYMENT_PAGE_CONTROLLER
                .initPaymentPage(initPaymentPageRequestDto)
                .getBodyAs(InitPaymentPageResponseDto.class)
                .getUrl();

        return new PaymentPage().openPage(pageUrl);
    }

    protected PaymentStatusPage payWithCard(PaymentPage paymentPage, CardEntity card) {
        return paymentPage
                .setCardNumber(card.getNumber())
                .setCardExpiryDate(card.getExpirationDate())
                .setCardCvv(card.getCvv())
                .submitPayment()
                .waitTillPaymentProcessed();
    }
}
