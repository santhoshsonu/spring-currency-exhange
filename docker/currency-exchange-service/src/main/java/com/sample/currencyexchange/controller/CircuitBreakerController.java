package com.sample.currencyexchange.controller;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/circuit-breaker")
@Slf4j
public class CircuitBreakerController {
  private static final String REMOTE_API = "http://localhost:8080/no-existing-api";

  @GetMapping
  // @Retry(name = "sample-api", fallbackMethod = "getFallbackResponse")
  @CircuitBreaker(name = "sample-api", fallbackMethod = "getFallbackResponse")
  @RateLimiter(name = "sample-api", fallbackMethod = "getRateLimiterFallbackResponse")
  public ResponseEntity<String> simpleCircuitBreakerAPI() {
    log.info("Calling Remote API: {}", REMOTE_API);
    ResponseEntity<String> responseEntity;
    responseEntity = new RestTemplate().getForEntity(REMOTE_API, String.class);
    return responseEntity;
  }

  private ResponseEntity<String> getFallbackResponse(RestClientException ex) {
    log.error("Remote API call error: {}", ex.getMessage());
    return new ResponseEntity<>("API Fallback Response", HttpStatus.OK);
  }

  private ResponseEntity<String> getFallbackResponse(CallNotPermittedException ex) {
    log.error("Circuit Breaker state is OPEN. Returning fallback response");
    return new ResponseEntity<>("Circuit Breaker fallback Response", HttpStatus.OK);
  }

  private ResponseEntity<String> getRateLimiterFallbackResponse(RequestNotPermitted ex) {
    log.error("API threshold exceeded. Returning RateLimit fallback response");
    return new ResponseEntity<>("RateLimit fallback Response", HttpStatus.OK);
  }
}
