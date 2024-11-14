package com.naveen.pauypwgback.controller;

import com.naveen.pauypwgback.dto.CheckOutResponse;
import com.naveen.pauypwgback.dto.CheckoutDto;
import com.naveen.pauypwgback.service.PaymentService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;
    @Operation(summary = "Checkout")
    @Tag(name = "Payment checkout",description = "Checkout for payment",
            externalDocs = @ExternalDocumentation(
                description = "Payment checkout",
                url = "https://docs.payu.in/docs/generate-hash-merchant-hosted"
            )
    )
    @PostMapping(value = "/checkout",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CheckOutResponse> checkout(
            @RequestBody @Validated CheckoutDto checkoutDto) {
        return ResponseEntity.ok(paymentService.checkout(checkoutDto));
    }
}
