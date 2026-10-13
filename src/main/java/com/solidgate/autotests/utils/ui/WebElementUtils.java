package com.solidgate.autotests.utils.ui;

import com.codeborne.selenide.*;
import lombok.experimental.*;
import lombok.extern.slf4j.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;
import static java.util.regex.Pattern.*;

@Slf4j
@UtilityClass
public class WebElementUtils {

    private static final String DATA_TEST_ID_TEMPLATE = "//*[@data-testid='%s']";
    private static final String CONTAINS_REGEX_TEMPLATE = ".*%s.*";
    private static final Duration LOADER_APPEAR_TIMEOUT = ofSeconds(5);
    private static final Duration LOADER_DISAPPEAR_TIMEOUT = ofSeconds(30);

    public static SelenideElement $dataTestId(String dataTestId) {
        return $x(DATA_TEST_ID_TEMPLATE.formatted(dataTestId));
    }

    public static String getVisibleText(SelenideElement element) {
        return element
                .shouldBe(visible, ofSeconds(10))
                .getText();
    }

    public static void waitTillElementAttributeDisappear(SelenideElement element, String attribute, String value) {
        var loaderCondition = attributeMatching(attribute, CONTAINS_REGEX_TEMPLATE.formatted(quote(value)));
        try {
            element.shouldHave(loaderCondition, LOADER_APPEAR_TIMEOUT);
        } catch (AssertionError e) {
            log.info("WAITER: loader {}='{}' did not appear", attribute, value);
        }

        element.shouldNotHave(loaderCondition, LOADER_DISAPPEAR_TIMEOUT);
    }
}
