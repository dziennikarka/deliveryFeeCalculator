# Delivery Fee Calculator

Delivery Fee Calculator estimates the delivery fee based on the value of customer's 
shopping cart, distance between the store and customer's location, number of items
in the shopping cart and possible rush hour coefficient. 

## Prerequisitives

* JRE11 or higher. You can get one from [JDK Dev Pages](https://jdk.dev/download/).
* Apache Maven to build and execute the application. You can get one from 
[Apache Website](https://maven.apache.org/download.cgi).

## Installation and Execution

Navigate to the root of the project via command line and execute the command:

```bash
mvn spring-boot:run
```
## Specification 

Delivery Code Calculator is needed when a customer is ready with their shopping cart and wants to see how much the delivery will cost. The delivery price depends on the cart value, the number of items in the cart, the time of the order, and the delivery distance.

**Rules for calculating a delivery fee:**

- If the cart value is less than 10€, a small order surcharge is added to the delivery price. The surcharge is the difference between the cart value and 10€. For example if the cart value is 8.90€, the surcharge will be 1.10€.

- A delivery fee for the first 1000 meters (=1km) is 2€. If the delivery distance is longer than that, 1€ is added for every additional 500 meters that the courier needs to travel before reaching the destination. Even if the distance would be shorter than 500 meters, the minimum fee is always 1€.

	- Example 1: If the delivery distance is 1499 meters, the delivery fee is: 2€ base fee + 1€ for the additional 500 m => 3€
	- Example 2: If the delivery distance is 1500 meters, the delivery fee is: 2€ base fee + 1€ for the additional 500 m => 3€
	- Example 3: If the delivery distance is 1501 meters, the delivery fee is: 2€ base fee + 1€ for the first 500 m + 1€ for the second 500 m => 4€

-	If the number of items is five or more, an additional 50 cent surcharge is added for each item above four

	- Example 1: If the number of items is 4, no extra surcharge
	- Example 2: If the number of items is 5, 50 cents surcharge is added
	- Example 3: If the number of items is 10, 3€ surcharge (6 x 50 cents) is added

-	The delivery fee can never be more than 15€, including possible surcharges.

-	The delivery is free (0€) when the cart value is equal or more than 100€.

- During the Friday rush (3 - 7 PM UTC), the delivery fee (the total fee including possible surcharges) will be multiplied by 1.1x. However, the fee still cannot be more than the max (15€).

## Backend specifics 

The goal is to implement an HTTP API (single endpoint) which calculates the delivery fee based on the information in the request payload (JSON) and includes the calculated delivery fee in the response payload (JSON).

### Request 

Example 

 > {"cart_value": 790, "delivery_distance": 2235, "number_of_items": 4, "time": "2021-10-12T13:00:00Z"}

Field Details 

| Field | Type | Description | Example value | 
|-------|------|-------------|---------------|
|cart_value|Integer|Value of the shopping cart **in cents**.|**790** (790 cents = 7.90€)|
|delivery_distance|Integer|The distance between the store and customer’s location **in meters**.|**2235** (2235 m = 2.235 km)|
|number_of_items|Integer|The number of items in the customer's shopping cart.|**4** (customer has 4 items in the cart)|
|time|String|Order time in ISO format.|**2021-01-16T13:00:00Z**|

### Response 

Example 

 > {"delivery_fee": 710} 

Field details

| Field | Type | Description | Example value | 
|-------|------|-------------|---------------|
|delivery_fee|Integer|Calculated delivery fee **in cents**.|**710** (710 cents = 7.10€)|

## Contributing 

The code is divided into two parts: 

* calculation engine 
* REST API 

Both parts are covered with tests. In case of changes tests should be 
taken into account.  

## License
[MIT](https://choosealicense.com/licenses/mit/)

