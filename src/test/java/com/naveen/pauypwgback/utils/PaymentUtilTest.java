package com.naveen.pauypwgback.utils;

import com.naveen.pauypwgback.dto.CheckOutResponse;
import com.naveen.pauypwgback.dto.CheckoutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PaymentUtilTest {

    @InjectMocks
    private PaymentUtil paymentUtil;

    @Mock
    private CheckoutDto checkoutDto;

    @Value("${payu.salt}")
    private String PAYU_SALT = "testSalt";

    @Value("${payu.key}")
    private String PAYU_KEY = "testKey";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHashAndTransactionId() {
        when(checkoutDto.amount()).thenReturn("500");
        when(checkoutDto.productInfo()).thenReturn("ProductInfo");
        when(checkoutDto.firstName()).thenReturn("John");
        when(checkoutDto.email()).thenReturn("john@example.com");

        CheckOutResponse response = paymentUtil.getHashAndTransactionId(checkoutDto);
        assertNotNull(response);
        assertNotNull(response.txnId());
        assertNotNull(response.hash());
        assertTrue(response.txnId().startsWith("T"));
        verify(checkoutDto, times(1)).amount();
        verify(checkoutDto, times(1)).productInfo();
        verify(checkoutDto, times(1)).firstName();
        verify(checkoutDto, times(1)).email();
    }

    @Test
    public void testGenerateHash() {
        String txnId = "T1234567890";
        String amount = "500";
        String productInfo = "ProductInfo";
        String firstName = "John";
        String email = "john@example.com";
        String hash = PaymentUtil.generateHash(PAYU_KEY, txnId, amount, productInfo, firstName, email, PAYU_SALT);
        assertNotNull(hash);
        assertEquals(128, hash.length());
    }
}
