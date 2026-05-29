package com.jamal.springai.response;

public record PostResponse(
        Integer userId,
        Integer id,
        String title,
        String body
) {
}