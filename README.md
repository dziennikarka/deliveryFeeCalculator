#Delivery Fee Calculator

Delivery Fee Calculator estimates the delivery fee based on the value of customer's 
shopping cart, distance between the store and customer's location, number of items
in the shopping cart and possible rush hour coefficient. 

##Prerequisitives

* JRE11 or higher. You can get one from [JDK Dev Pages](https://jdk.dev/download/).
* Apache Maven to build and execute the application. You can get one from 
[Apache Website](https://maven.apache.org/download.cgi).

##Installation and Execution

Navigate to the root of the project via command line and execute the command:

```bash
mvn spring-boot:run
```

##Contributing 

The code is divided into two parts: 

* calculation engine 
* REST API 

Both parts are covered with tests. In case of changes tests should be 
taken into account.  

##License
[MIT](https://choosealicense.com/licenses/mit/)

