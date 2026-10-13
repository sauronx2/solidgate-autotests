package com.solidgate.autotests.utils.api;

import lombok.*;
import lombok.experimental.*;

import javax.crypto.spec.*;

import static java.nio.charset.StandardCharsets.*;
import static java.util.Base64.*;
import static java.util.HexFormat.*;
import static javax.crypto.Mac.*;

@UtilityClass
public class RequestSignatureUtils {

    private static final String HMAC_ALGORITHM = "HmacSHA512";

    @SneakyThrows
    public static String signRequestBody(String publicKey, String secretKey, String requestBody) {
        var hmac = getInstance(HMAC_ALGORITHM);
        hmac.init(new SecretKeySpec(secretKey.getBytes(UTF_8), HMAC_ALGORITHM));
        var signedPayload = publicKey + requestBody + publicKey;
        var hexDigest = of().formatHex(hmac.doFinal(signedPayload.getBytes(UTF_8)));

        return getEncoder().encodeToString(hexDigest.getBytes(UTF_8));
    }
}
