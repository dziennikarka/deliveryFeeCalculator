package com.example.deliveryFeeCalculatorBackend.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Represents calculator, whose task is calculationg of delivery fee
 */
public class Calculator {
    /**
     * Value of the cart in cents that entitles to free delivery
     */
    private static final int CART_HUNDRED_EUROS = 10000;
    /**
     * Value for surcharge calculation: if the cart is less than 10 €,
     * customer needs to pay surcharge that is difference between their cart and surcharge limit
     */
    private static final int SURCHARGE_MAX = 1000;
    /**
     * Kilometer base fee that is added to delivery fee if the distance is 1 km or less
     */
    private static final int KILOMETER_BASE_FEE = 200;
    /**
     * Extra fee that is added for every additional 500 meters (even if the distance is shorter than 500 m)
     */
    private static final int DISTANCE_EXTRA_FEE = 100;
    /**
     * Surcharge per item starting from the 5th item in the cart
     */
    private static final int SURCHARGE_PER_ITEM = 50;
    /**
     * Distance covered by kilometer base fee
     */
    private static final int DISTANCE_MINIMUM_METERS = 1000;
    /**
     * Distance that should be covered by distance extra fee: 1 € is paid for every extra 1 - 500 m
     */
    private static final int EXTRA_METERS = 500;
    /**
     * number of items that are not a subject for an additional item surcharge
     */
    private static final int MAXIMUM_ITEMS_FREE = 4;
    /**
     * maximum possible delivery fee
     */
    private static final int FEE_MAXIMUM = 1500;
    /**
     * Day of rush hour deliveries
     */
    private static final String RUSH_DAY = "friday";
    /**
     * Starting time of rush hour deliveries
     */
    public static final int RUSH_START = 15;
    /**
     * Ending time of rush hour deliveries
     */
    public static final int RUSH_END = 19;

    /**
     * Calculates delivery fee based on cart total, delivery distance, number of items and time of delivery and estimates delivery fee in cents.
     *
     * @param cartTotal        total of customer's cart in cents
     * @param deliveryDistance how far the goods should be delivered, distance expressed in meters
     * @param items            a number of items in the customer cart
     * @param time             time when order takes place in ISO format, UTC time zone
     * @return calculated delivery fee in cents
     */
    public int calculateDeliveryFee(int cartTotal, int deliveryDistance, int items, String time) {
        int deliveryFee = 0;
        //cart total more than 100, delivery fee is 0
        if (cartTotal >= CART_HUNDRED_EUROS) {
            deliveryFee = 0;
        } else {
            //calculation of surcharge if cart total is less than 10
            if (cartTotal < SURCHARGE_MAX) {
                deliveryFee = SURCHARGE_MAX - cartTotal;
            }
            deliveryFee += calculateDistanceFee(deliveryDistance);
            deliveryFee += calculateSurchargePerItem(items);

            if (checkIfRushHour(time)) {
                //coefficient for the rush hour 1.1
                deliveryFee += deliveryFee / 10;
            }

            if (deliveryFee > FEE_MAXIMUM) {
                deliveryFee = FEE_MAXIMUM;
            }
        }
        return deliveryFee;
    }

    /**
     * calculates delivery fee based on the distance between the store and customer's location.
     * There is a base fee for the first kilometer and an extra fee for every 1 - 500 m
     * @param distanceInMeters delivery distance in meters
     * @return fee calculated based on the distance in cents
     */
    private int calculateDistanceFee(int distanceInMeters) {
        if (distanceInMeters <= DISTANCE_MINIMUM_METERS) {
            return KILOMETER_BASE_FEE;
        }
        double coefficient = Math.ceil(1.0 * (distanceInMeters - DISTANCE_MINIMUM_METERS) / EXTRA_METERS);
        return KILOMETER_BASE_FEE + (DISTANCE_EXTRA_FEE * (int) coefficient);
    }

    /**
     * calculates fee based on the number of items in the shopping cart. There is an extra surcharge for every item
     * starting from the fifth.
     * @param items a number of items in the shopping cart
     * @return fee calculated based on the number of items in the shopping cart
     */
    private int calculateSurchargePerItem(int items) {
        if (items > MAXIMUM_ITEMS_FREE) {
            return (items - MAXIMUM_ITEMS_FREE) * SURCHARGE_PER_ITEM;
        }
        return 0;
    }

    /**
     * checks whether the order date is Friday rush hour (15.00 - 18.59)
     * @param time an order day in ISO format, UTC time zone
     * @return true of it is rush hour and false otherwise
     */
    private Boolean checkIfRushHour(String time) {
        LocalDateTime timeObj = LocalDateTime.parse(time, DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("UTC")));
        String dayOfWeek = timeObj.getDayOfWeek().toString().toLowerCase();

        if (dayOfWeek.equals(RUSH_DAY)) {
            if (timeObj.getHour() >= RUSH_START && timeObj.getHour() < RUSH_END) {
                return true;
            }
        }
        return false;
    }

}

