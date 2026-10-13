package com.solidgate.autotests.tests.cardpayment;

import com.solidgate.autotests.models.api.cardpayments.responses.OrderStatusResponseDto.*;
import org.junit.jupiter.api.*;

import static com.solidgate.autotests.models.entities.MoneyEntity.*;
import static com.solidgate.autotests.models.enums.OrderStatus.*;
import static com.solidgate.autotests.models.enums.PaymentStatus.*;
import static com.solidgate.autotests.models.enums.SandboxCard.*;
import static com.solidgate.autotests.models.enums.TransactionOperation.*;
import static com.solidgate.autotests.models.enums.TransactionStatus.*;
import static com.solidgate.autotests.models.repos.api.InitPaymentPageRequestDtoRepo.*;
import static com.solidgate.autotests.models.repos.entities.CardEntityRepo.*;
import static com.solidgate.autotests.utils.MoneyUtils.*;
import static org.assertj.core.api.SoftAssertions.*;

public class PaidOrderStatusMatchesPaymentPageTest extends AbstractCardPaymentTestRunner {

    @BeforeEach
    void prepareDefaultOrderAndSuccessfulPaymentCard() {
        initPaymentPageRequestDto = getInitPaymentPageRequestDto(DEFAULT_ORDER_MONEY);
        card = getCardEntity(SUCCESSFUL_PAYMENT);
    }

    @Test
    @DisplayName("Paid order status shows the amount and currency displayed on the Payment Page")
    public void verifyPaidOrderStatusMatchesPaymentPageTest() {
        var paymentPage = createOrderAndOpenPaymentPage(initPaymentPageRequestDto);
        var pageMoney = parseDisplayedMoney(paymentPage.getOrderPriceText());

        payWithCard(paymentPage, card).waitTillPaymentStatusAppears(SUCCESSFUL);

        var orderId = initPaymentPageRequestDto
                .getOrder()
                .getOrderId();
        var statusResponseDto = CARD_PAYMENTS_CONTROLLER.waitForTerminalOrderStatus(orderId);
        var order = statusResponseDto.getOrder();
        var statusMoney = of(order.getAmount(), order.getCurrency());
        var transactions = statusResponseDto
                .getTransactions()
                .values();
        assertSoftly(softly -> {
            softly.assertThat(statusMoney)
                    .as("Order amount and currency should equal the values shown on the Payment Page")
                    .isEqualTo(pageMoney);
            softly.assertThat(order.getStatus())
                    .as("Order status should be auth_ok after a successful card payment")
                    .isEqualTo(AUTH_OK);
            softly.assertThat(order.getAuthorizedAmount())
                    .as("Authorized amount should equal the order amount")
                    .isEqualTo(order.getAmount());
            softly.assertThat(transactions)
                    .as("Order should hold exactly one successful auth transaction")
                    .singleElement()
                    .extracting(TransactionDto::getOperation, TransactionDto::getStatus)
                    .containsExactly(AUTH, SUCCESS);
        });
    }
}
