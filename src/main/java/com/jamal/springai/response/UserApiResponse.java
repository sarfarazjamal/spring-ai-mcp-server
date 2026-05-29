package com.jamal.springai.response;

public record UserApiResponse(
        Integer id,
        String name,
        String email,
        String phone
) {
}