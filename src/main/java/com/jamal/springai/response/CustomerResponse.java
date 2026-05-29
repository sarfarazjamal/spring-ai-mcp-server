package com.jamal.springai.response;

public record CustomerResponse(
        Integer customerId,
        String name,
        String email,
        String phone,
        String status
) {
}