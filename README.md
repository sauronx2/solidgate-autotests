# solidgate-autotests

Automated tests for the Solidgate Payment Page: an order is created through `/init`, paid in the browser with a
sandbox card, and verified on the success page and through `/status`.

## Tests

| Test | What it checks |
|---|---|
| `SuccessfulCardPaymentShowsSuccessPageTest` | A Payment Page is created and paid through the UI; the page with the `Payment successful!` message opens and shows the paid amount and currency |
| `PaidOrderStatusMatchesPaymentPageTest` | `/status` of the paid order returns the amount and currency displayed on the Payment Page, `order.status = auth_ok`, the authorized amount equals the order amount, and the order holds exactly one successful `auth` transaction |

Every test creates and pays its own order in the sandbox; the amount and currency are read from the Payment Page
itself, not from the request that created it.

## Stack

Java 25, Gradle 9 (Kotlin DSL, version catalog), JUnit 6, Selenide 7, REST Assured 6, Jackson 3, AssertJ, Awaitility,
Lombok. Versions are pinned in `gradle/libs.versions.toml`.

## Prerequisites

- Any JDK 17+ to start Gradle; the Java 25 toolchain is downloaded automatically on the first build.
- Google Chrome.
- Solidgate sandbox keys (public and secret).

## Setup

```shell
cp .env.example .env
```

Fill in `SOLIDGATE_PUBLIC_KEY` and `SOLIDGATE_SECRET_KEY`. `.env` is ignored by git. Environment variables with the same
names override `.env`, and system properties (`-DSOLIDGATE_GATE_URL=...`) override both. Missing variables are reported
together at startup.

| Variable | Meaning |
|---|---|
| `SOLIDGATE_PUBLIC_KEY` | merchant public key (`api_pk_…`) |
| `SOLIDGATE_SECRET_KEY` | merchant secret key (`api_sk_…`) |
| `SOLIDGATE_PAYMENT_PAGE_URL` | Payment Page API base URL |
| `SOLIDGATE_GATE_URL` | Gate API base URL (`/status`) |

## Run

```shell
./gradlew test                                                   # all tests
./gradlew test --tests "*PaidOrderStatusMatchesPaymentPageTest"  # one test class
```

The tests open a visible Chrome window. The test task always re-runs the tests. Requests are signed with HMAC-SHA512 as described in the Solidgate API
documentation; the `merchant` and `signature` headers are redacted from logs and reports.

## Report

The Gradle HTML report is written to `build/reports/tests/test/index.html` after every run. The page of each test
holds its console output: every request and response of `/init` and `/status` with the `merchant` and `signature`
headers masked. A failed test saves a screenshot and the page source to `build/reports/tests/`; the paths are printed
in the failure output.

## Project map

```
src/main/java/com/solidgate/autotests/
  config/                  SolidgateConfig: keys and URLs from system properties, environment, .env
  api/restassuredclient/   REST Assured client factory, request signature filter, masked request log, response wrapper
  api/clients/             one HTTP call per endpoint: paymentpage (/init), cardpayments (/status)
  api/controllers/         API steps on top of the clients, /status polling until a terminal status
  models/api/              request and response DTOs per endpoint
  models/entities/         MoneyEntity (minor units + ISO currency), CardEntity
  models/enums/            order, transaction and payment statuses, sandbox cards, currencies
  models/repos/            test data factories: Payment Page request, card
  pages/paymentpage/       PaymentPage and PaymentStatusPage page objects
  utils/                   money parsing, signature, JSON, random data, waiters
  exceptions/              FrameworkException
src/test/java/com/solidgate/autotests/
  runners/                 CardPaymentTestSuite, the entry point of the Gradle test task
  tests/                   AbstractTestRunner: browser configuration and teardown
  tests/cardpayment/       AbstractCardPaymentTestRunner (create, open, pay) and the two tests
  extensions/              screenshot and page source on a failed test
```
