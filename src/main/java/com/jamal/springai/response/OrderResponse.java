package com.jamal.springai.response;

public record OrderResponse(
        Integer orderId,
        Integer customerId,
        String orderTitle,
        String orderDescription,
        String status
) {
}