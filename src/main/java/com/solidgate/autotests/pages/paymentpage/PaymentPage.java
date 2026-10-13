package com.solidgate.autotests.pages.paymentpage;

import com.codeborne.selenide.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.solidgate.autotests.utils.DateTimeUtils.*;
import static com.solidgate.autotests.utils.ui.WebElementUtils.*;

public class PaymentPage {

    private final SelenideElement submitButton = $dataTestId("submit");

    public PaymentPage openPage(String path) {
        open(path);

        return this;
    }

    public String getOrderPriceText() {
        var orderPriceText = $dataTestId("price_major");

        return getVisibleText(orderPriceText);
    }

    public PaymentPage setCardNumber(String cardNumber) {
        $dataTestId("cardNumber")
                .shouldBe(editable)
                .setValue(cardNumber);

        return this;
    }

    public PaymentPage setCardExpiryDate(YearMonth expiryDate) {
        $dataTestId("cardExpiryDate")
                .shouldBe(editable)
                .setValue(expiryDate.format(MM_YY));

        return this;
    }

    public PaymentPage setCardCvv(String cvv) {
        $dataTestId("cardCvv")
                .shouldBe(editable)
                .setValue(cvv);

        return this;
    }

    public PaymentPage submitPayment() {
        submitButton
                .shouldBe(clickable)
                .click();

        return this;
    }

    public PaymentStatusPage waitTillPaymentProcessed() {
        waitTillElementAttributeDisappear(submitButton, "class", "SubmitButton_processing");

        return new PaymentStatusPage();
    }
}
