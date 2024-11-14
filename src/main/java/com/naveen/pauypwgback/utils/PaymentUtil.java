package com.naveen.pauypwgback.utils;

import com.naveen.pauypwgback.dto.CheckOutResponse;
import com.naveen.pauypwgback.dto.CheckoutDto;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Log4j2
@Component
public class PaymentUtil {

    @Value("${payu.salt}")
    private String PAYU_SALT;

    @Value("${payu.key}")
    private String PAYU_KEY;

    @NonNull
    private String generateTransactionId() {
        Date timestamp = new Date();
        int random = (int) Math.round(Math.random() * 10000);
        return "T" + timestamp.getTime() + random;
    }

    @NonNull
    static String generateHash(String key, String txnId, String amount, String productInfo, String firstname, String email, String salt) {
        String input = key+"|"+txnId+"|"+amount+"|"+productInfo+"|"+firstname+"|"+email+"|||||||||||"+salt;
        return sha512(input);
    }

    @NonNull
    private static String sha512(@NonNull String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("Error while generating hash {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Validated @NonNull
    public CheckOutResponse getHashAndTransactionId(@Validated @NonNull CheckoutDto checkoutDto) {
            String amount = checkoutDto.amount();
            String productInfo = checkoutDto.productInfo();
            String firstName = checkoutDto.firstName();
            String email = checkoutDto.email();
            String txnId = generateTransactionId();
            String hash = generateHash(PAYU_KEY, txnId, amount, productInfo, firstName, email, PAYU_SALT);
            log.info("Generated hash {} for transaction id {}", hash, txnId);
            return new CheckOutResponse(txnId, hash);
    }
}
