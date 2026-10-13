package com.solidgate.autotests.pages.paymentpage;

import com.solidgate.autotests.models.enums.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.solidgate.autotests.utils.ui.WebElementUtils.*;
import static java.lang.String.*;
import static java.time.Duration.*;

public class PaymentStatusPage {

    private static final String STATUS_TITLE_TEMPLATE = "//h1[@data-testid='status-title' and text()='%s']";

    public PaymentStatusPage waitTillPaymentStatusAppears(PaymentStatus status) {
        $x(format(STATUS_TITLE_TEMPLATE, status.getTitle())).shouldBe(visible, ofSeconds(15));

        return this;
    }

    public String getStatusTitleText() {
        var statusTitle = $dataTestId("status-title");

        return getVisibleText(statusTitle);
    }

    public String getOrderPriceText() {
        var orderPriceText = $dataTestId("status-order-price-header");

        return getVisibleText(orderPriceText);
    }
}
