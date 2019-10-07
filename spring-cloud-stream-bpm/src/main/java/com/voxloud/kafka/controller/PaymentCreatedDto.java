package com.voxloud.kafka.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentCreatedDto {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("payment_id")
    private Long paymentId;
    
    private String type;
}
