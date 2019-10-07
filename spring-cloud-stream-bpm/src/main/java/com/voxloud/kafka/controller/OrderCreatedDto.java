package com.voxloud.kafka.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderCreatedDto {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("order_id")
    private Long orderId;

    private int amount;

    @JsonProperty("item_id")
    private String itemId;
}
