package com.naveen.pauypwgback.service.impl;

import com.naveen.pauypwgback.dto.CheckOutResponse;
import com.naveen.pauypwgback.dto.CheckoutDto;
import com.naveen.pauypwgback.service.PaymentService;
import com.naveen.pauypwgback.utils.PaymentUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentUtil paymentUtil;
    @Override
    public CheckOutResponse checkout(@NonNull CheckoutDto checkoutDto) {
        if(!checkoutDto.isValidCheckoutDto()){
            throw new IllegalArgumentException("Invalid checkoutDto");
        }
        return paymentUtil.getHashAndTransactionId(checkoutDto);
    }
}
