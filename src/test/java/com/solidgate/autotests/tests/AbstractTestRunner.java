package com.solidgate.autotests.tests;

import com.solidgate.autotests.extensions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith(FailedTestScreenshotExtension.class)
public abstract class AbstractTestRunner {

    @BeforeAll
    static void configureBrowser() {
        browser = "chrome";
        browserSize = null;
        headless = false;
        timeout = 10_000;
    }

    @AfterEach
    void closeDriver() {
        closeWebDriver();
    }
}
