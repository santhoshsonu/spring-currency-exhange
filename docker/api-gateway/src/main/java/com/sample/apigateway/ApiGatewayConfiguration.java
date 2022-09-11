package com.sample.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            route ->
                route
                    .path("/get")
                    .filters(
                        f ->
                            f.addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("MyParam", "MyValue"))
                    .uri("http://httpbin.org:80"))
        .route(
            route -> route.path("/api/currency-exchange/**").uri("lb://currency-exchange-service"))
        .route(
            route ->
                route.path("/api/currency-conversion/**").uri("lb://currency-conversion-service"))
        .build();
  }
}
