package com.example.deliveryFeeCalculatorBackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents response payload which consists of delivery fee calculated in cents
 */
@Data
@AllArgsConstructor
public class Fee {
    /**
     * delivery fee calculated in cents
     */
    @JsonProperty("delivery_fee")
    private int deliveryFee;
}
