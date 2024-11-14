package com.naveen.pauypwgback.service;

import com.naveen.pauypwgback.dto.CheckOutResponse;
import com.naveen.pauypwgback.dto.CheckoutDto;

@FunctionalInterface
public interface PaymentService {
     CheckOutResponse checkout(CheckoutDto checkoutDto) ;
}
