/*
* Copyright 2025 - 2025 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package com.jamal.springai;

import java.time.LocalDateTime;
import java.util.Map;

import com.jamal.springai.response.CustomerResponse;
import com.jamal.springai.response.OrderResponse;
import com.jamal.springai.response.PostResponse;
import com.jamal.springai.response.UserApiResponse;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class Tools {

    private final WebClient webClient;

    public Tools(WebClient.Builder builder) {
        this.webClient = builder.build();
    }


    @McpTool(description = "Returns the current server time")
    public Map<String, Object> timeNow() {
        return Map.of("now", LocalDateTime.now().toString());
    }

	@McpTool(description = "Greets a person by name")
    public Map<String, Object> greet(String name) {
        return Map.of("message", "Hello " + name + " 👋");
    }


    @McpTool(description = "Get order information")
    public OrderResponse getOrder(Integer orderId) {

        String url = "https://jsonplaceholder.typicode.com/posts/" + orderId;

        PostResponse response = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(PostResponse.class)
                .block();

        return new OrderResponse(
                response.id(),
                response.userId(),
                response.title(),
                response.body(),
                "CONFIRMED"
        );
    }

    @McpTool(description = "Get customer information")
    public CustomerResponse getCustomer(Integer customerId) {

        UserApiResponse response = webClient
                .get()
                .uri("https://jsonplaceholder.typicode.com/users/{id}", customerId)
                .retrieve()
                .bodyToMono(UserApiResponse.class)
                .block();

        return new CustomerResponse(
                response.id(),
                response.name(),
                response.email(),
                response.phone(),
                "ACTIVE"
        );
    }
}