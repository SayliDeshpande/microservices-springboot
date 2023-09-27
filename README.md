# Online Shopping Application 🤩
This repository contains the latest source code of the spring-boot-microservices - online shopping App

# Microservices Architectural patterns implemented 
 * Service Discovery
 * Centralized Configuration
 * Distributed Tracing
 * Event Driven Architecture
 * Centralized Logging
 * Circuit Breaker
 * Secure Microservices using Keycloak

# Services built :
 * Product Service - Create and view products, acts as a product catalog.
 * Order Service - Order a product.
 * Inventory Service - Order service will check in inventory if product is in stock or not before placing an order.
 * Notification service - This will send notifications after order is placed.

# High Level Architecture : 
![SolutionArchitecture](https://github.com/SayliDeshpande/microservices-springboot/assets/44116052/cb50b2e9-c4a2-497d-9922-834a76ff6864)

# Logical Architecture :

![logicalDiag](https://github.com/SayliDeshpande/microservices-springboot/assets/44116052/95479ca0-7f91-45d5-9d2c-711ea2847f61)

![APIGateway](https://github.com/SayliDeshpande/microservices-springboot/assets/44116052/7b754ffa-09fe-4aa1-a33d-f73eb845e3cd)


# How to run this application without docker 

1. Run mvn clean verify -DskipTests by going inside each folder to build the applications.
2. After that run mvn spring-boot:run by going inside each folder to start the applications.

# How to run this application using docker

1. Run mvn clean verify -DskipTests by going inside each folder to build the applications.
2. After that run mvn spring-boot:run by going inside each folder to start the applications.

# Testing results 

![OrderPlacedSuccessfully](https://github.com/SayliDeshpande/microservices-springboot/assets/44116052/f01ee8d1-3c93-468f-818f-3cea425af5c8)

![GetProductInfo](https://github.com/SayliDeshpande/microservices-springboot/assets/44116052/34b8c426-2846-4e9f-bc40-bf3690609c4f)

