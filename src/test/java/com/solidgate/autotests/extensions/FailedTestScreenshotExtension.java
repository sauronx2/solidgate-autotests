package com.solidgate.autotests.extensions;

import com.codeborne.selenide.ex.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.extension.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

@Slf4j
public class FailedTestScreenshotExtension implements TestExecutionExceptionHandler,
        LifecycleMethodExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        saveBrowserEvidenceAndRethrow(context, throwable);
    }

    @Override
    public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        saveBrowserEvidenceAndRethrow(context, throwable);
    }

    private static void saveBrowserEvidenceAndRethrow(ExtensionContext context, Throwable throwable)
            throws Throwable {
        if (hasWebDriverStarted() && !(throwable instanceof UIAssertionError)) {
            var screenshotPath = screenshot(context.getRequiredTestClass().getSimpleName());
            log.info("Screenshot and page source of the failed test: {}", screenshotPath);
        }

        throw throwable;
    }
}
