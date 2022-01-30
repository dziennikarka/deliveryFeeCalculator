package com.example.deliveryFeeCalculatorBackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents request payload that includes value of the shopping cart in cents, the distance between the store and customer location in meters,
 * the number of items in the customer's shopping cart, order time in ISO format
 */
@Data
public class FeeRequest {
    /**
     * value of the shopping cart in cents
     */
    @JsonProperty("cart_value")
    private int cartValue;

    /**
     * distance between the store and customer location in meters
     */
    @JsonProperty("delivery_distance")
    private int deliveryDistance;

    /**
     * number of items in the customer's shopping cart
     */
    @JsonProperty("number_of_items")
    private int numberOfItems;

    /**
     * order time in ISO format
     */
    private String time;
}
