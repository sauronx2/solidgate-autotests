package com.solidgate.autotests.tests.cardpayment;

import org.junit.jupiter.api.*;

import static com.solidgate.autotests.models.enums.PaymentStatus.*;
import static com.solidgate.autotests.models.enums.SandboxCard.*;
import static com.solidgate.autotests.models.repos.api.InitPaymentPageRequestDtoRepo.*;
import static com.solidgate.autotests.models.repos.entities.CardEntityRepo.*;
import static com.solidgate.autotests.utils.MoneyUtils.*;
import static org.assertj.core.api.SoftAssertions.*;

public class SuccessfulCardPaymentShowsSuccessPageTest extends AbstractCardPaymentTestRunner {

    @BeforeEach
    void prepareDefaultOrderAndSuccessfulPaymentCard() {
        initPaymentPageRequestDto = getInitPaymentPageRequestDto(DEFAULT_ORDER_MONEY);
        card = getCardEntity(SUCCESSFUL_PAYMENT);
    }

    @Test
    @DisplayName("Successful card payment shows the success page")
    public void verifySuccessfulCardPaymentShowsSuccessPageTest() {
        var paymentPage = createOrderAndOpenPaymentPage(initPaymentPageRequestDto);
        var pageMoney = parseDisplayedMoney(paymentPage.getOrderPriceText());
        var statusPage = payWithCard(paymentPage, card).waitTillPaymentStatusAppears(SUCCESSFUL);
        var statusPageMoney = parseDisplayedMoney(statusPage.getOrderPriceText());
        assertSoftly(softly -> {
            softly.assertThat(statusPage.getStatusTitleText())
                    .as("Status title should confirm the successful payment")
                    .isEqualTo(SUCCESSFUL.getTitle());
            softly.assertThat(statusPageMoney)
                    .as("Success page should show the amount and currency of the paid order")
                    .isEqualTo(pageMoney);
        });
    }
}
