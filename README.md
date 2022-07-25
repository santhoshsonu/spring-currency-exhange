# spring-currency-exhange
Currency exchange backend services using Spring Cloud based on Master Microservices with Spring Boot and Spring Cloud [Udemy] by in28Minutes Official

# Docker
## Images

- https://hub.docker.com/u/armosdocker
- Currency Exchange Service
    - armosdocker/spring-microservices-currency-exchange-service:0.0.1-SNAPSHOT
- Currency Conversion Service
    - armosdocker/spring-microservices-currency-conversion-service:0.0.1-SNAPSHOT
- Eureka
    - armosdocker/spring-microservices-naming-server:0.0.1-SNAPSHOT
- API GATEWAY
    - armosdocker/spring-microservices-api-gateway:0.0.1-SNAPSHOT

## Build
- Build image for each service/project by running the maven goal:
  - <code>mvn spring-boot:build-image</code>
  - <code>mvn spring-boot:build-image -DskipTests</code> <i>(Ignores App tests - recommended for test env only)</i>
 
## URLS

#### Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/INR

#### Currency Conversion Service
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

#### Eureka
- http://localhost:8761/

#### Zipkin
- http://localhost:9411/

#### API GATEWAY
- http://localhost:8765/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10

#### Commands
```
docker run -p 9411:9411 openzipkin/zipkin:2.23
docker push docker.io/armosdocker/spring-microservices-currency-exchange-service:0.0.1-SNAPSHOT
docker-compose --version
docker-compose up
docker push armosdocker/spring-microservices-naming-server:0.0.1-SNAPSHOT
docker push armosdocker/spring-microservices-currency-conversion-service:0.0.1-SNAPSHOT
docker push armosdocker/spring-microservices-api-gateway:0.0.1-SNAPSHOT
watch -n 0.1 curl http://localhost:8000/sample-api