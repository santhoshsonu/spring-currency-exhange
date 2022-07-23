package com.sample.currencyconversion.remote;

import com.sample.currencyconversion.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Configure Feign client to call currency-exchange service
 * to fetch the exchange rates.
 * Automatically picks up instance url from Eureka client using the service name
 * */
//@FeignClient(name = "currency-exchange-service", url = "${remote.currency-exchange.base-url}")
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeFeignClient {
  @GetMapping(value = "/api/currency-exchange/from/{from}/to/{to}")
  CurrencyConversion getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to);
}
