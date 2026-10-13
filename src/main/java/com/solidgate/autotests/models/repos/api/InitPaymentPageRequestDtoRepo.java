package com.solidgate.autotests.models.repos.api;

import com.solidgate.autotests.models.api.paymentpage.requests.*;
import com.solidgate.autotests.models.api.paymentpage.requests.InitPaymentPageRequestDto.*;
import com.solidgate.autotests.models.entities.*;
import lombok.experimental.*;

import static com.solidgate.autotests.models.enums.FiatCurrency.*;
import static com.solidgate.autotests.models.enums.OrderType.*;
import static com.solidgate.autotests.utils.RandomUtils.*;

@UtilityClass
public class InitPaymentPageRequestDtoRepo {

    public static final MoneyEntity DEFAULT_ORDER_MONEY = new MoneyEntity(1020, EUR.getCurrency());

    public static InitPaymentPageRequestDto getInitPaymentPageRequestDto(MoneyEntity money) {
        var uniqueSuffix = getRandomUUIDv4Suffix();
        var orderDto = OrderDto
                .builder()
                .orderId("order-" + getRandomUUIDv4AsString())
                .amount(money.getMinorUnits())
                .currency(money.getCurrencyCode())
                .orderDescription("Order " + uniqueSuffix)
                .type(AUTH)
                .customerEmail("buyer.%s@example.com".formatted(uniqueSuffix))
                .build();
        var pageCustomization = new PageCustomizationDto("Shop " + uniqueSuffix);

        return new InitPaymentPageRequestDto(orderDto, pageCustomization);
    }
}
