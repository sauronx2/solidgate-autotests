package com.solidgate.autotests.models.api.cardpayments.responses;

import com.solidgate.autotests.models.api.shared.*;
import com.solidgate.autotests.models.enums.*;
import lombok.*;
import tools.jackson.databind.*;
import tools.jackson.databind.PropertyNamingStrategies.*;
import tools.jackson.databind.annotation.*;

import java.util.*;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class OrderStatusResponseDto {

    private OrderDto order;
    private TransactionDto transaction;
    private Map<String, TransactionDto> transactions;
    private Map<String, ChargebackDto> chargebacks;
    private AntifraudResultDto antifraudResult;
    private VerificationResultDto verificationResult;
    private ThreeDsDto threeDs;
    private PayFormDto payForm;
    private PaymentAdviserDto paymentAdviser;
    private String redirectUrl;
    private String verifyUrl;
    private JsonNode orderMetadata;
    private DeviceInfoDto deviceInfo;
    private RoutingDto routing;
    private ApiErrorDto error;

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class OrderDto {

        private String id;
        private String orderId;
        private Long amount;
        private String currency;
        private String mid;
        private String descriptor;
        private Boolean fraudulent;
        private Long marketingAmount;
        private String marketingCurrency;
        private Long processingAmount;
        private String processingCurrency;
        private OrderStatus status;
        private Long refundedAmount;
        private Long authorizedAmount;
        private String orderDescription;
        private String customerEmail;
        private String customerAccountId;
        private String authCode;
        private String ipAddress;
        private String paymentMethod;
        private String pspOrderId;
        private String providerPaymentId;
        private String geoCountry;
        private String paymentType;
        private String productId;
        private String productName;
        private String subscriptionId;
        private String trafficSource;
        private ShippingAddressDto shippingAddress;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class ShippingAddressDto {

        private String address;
        private String city;
        private String country;
        private String state;
        private String zip;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class TransactionDto {

        private String id;
        private TransactionOperation operation;
        private TransactionStatus status;
        private String descriptor;
        private Long amount;
        private String currency;
        private String createdAt;
        private String updatedAt;
        private Long marketingAmount;
        private String marketingCurrency;
        private BillingDetailsDto billingDetails;
        private CardDto card;
        private CardTokenDto cardToken;
        private String schemeTransactionId;
        private String transactionLinkId;
        private String authorizationType;
        private String arnCode;
        private String rrnCode;
        private String paymentAccountReference;
        private String refundReason;
        private String refundReasonCode;
        private ApiErrorDto error;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class BillingDetailsDto {

        private String address;
        private String city;
        private String country;
        private String state;
        private String zip;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class CardDto {

        private String bank;
        private String bin;
        private String brand;
        private String cardCategory;
        private String cardHolder;
        private String cardExpMonth;
        private Integer cardExpYear;
        private String cardId;
        private CardTokenDto cardToken;
        private String cardType;
        private String country;
        private Boolean isReloadable;
        private String number;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class ThreeDsDto {

        private String eci;
        private String flow;
        private String exception;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class PayFormDto {

        private String token;
        private String formUrl;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class PaymentAdviserDto {

        private String advise;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class DeviceInfoDto {

        private String userAgent;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class RoutingDto {

        private List<CascadeStepDto> cascadeSteps;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class CascadeStepDto {

        private String mid;
        private String midDescriptor;
        private String routeId;
        private Integer cascadeNumber;
        private String segmentId;
        private Long legalEntityId;
        private String legalEntityName;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class ChargebackDto {

        private Long id;
        private Long amount;
        private String currency;
        private String status;
        private String type;
        private String reasonCode;
        private String reasonDescription;
        private String reasonGroup;
        private String disputeDate;
        private String settlementDate;
        private List<ChargebackFlowDto> chargebackFlow;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class ChargebackFlowDto {

        private Long id;
        private Long amount;
        private Long disputeAmount;
        private String currency;
        private String status;
        private String type;
        private String arnCode;
        private String date;
        private String deadlineDate;
        private String settlementDate;
        private String updatedDate;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class AntifraudResultDto {

        private String preAuth;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class VerificationResultDto {

        private String avsResult;
        private String cvvResult;
        private String nameValidationResult;
        private AniDto ani;
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class AniDto {

        private String status;
        private String firstNameMatch;
        private String lastNameMatch;
        private String fullNameMatch;
    }
}
