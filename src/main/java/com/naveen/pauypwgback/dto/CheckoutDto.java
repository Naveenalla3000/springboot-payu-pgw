package com.naveen.pauypwgback.dto;

public record CheckoutDto (
     String productInfo,
     String amount,
     String firstName,
     String email
){
    public boolean isValidCheckoutDto(){
        return productInfo != null && amount != null && firstName != null && email != null;
    }
}