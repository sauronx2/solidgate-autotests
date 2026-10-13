package com.solidgate.autotests.models.repos.entities;

import com.solidgate.autotests.models.entities.*;
import com.solidgate.autotests.models.enums.*;
import lombok.experimental.*;

import static com.solidgate.autotests.utils.DateTimeUtils.*;
import static com.solidgate.autotests.utils.RandomUtils.*;
import static java.lang.String.*;

@UtilityClass
public class CardEntityRepo {

    private static final int CVV_LENGTH = 3;

    public static CardEntity getCardEntity(SandboxCard sandboxCard) {
        var cvv = valueOf(getRandomNumberWithDigits(CVV_LENGTH));

        return CardEntity
                .builder()
                .number(sandboxCard.getNumber())
                .expirationDate(getRandomCardExpirationDate())
                .cvv(cvv)
                .build();
    }
}
