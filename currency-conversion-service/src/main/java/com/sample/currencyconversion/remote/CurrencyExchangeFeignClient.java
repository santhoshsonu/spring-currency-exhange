package com.sample.currencyconversion.remote;

import com.sample.currencyconversion.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange", url = "${remote.currency-exchange.base-url}")
public interface CurrencyExchangeFeignClient {
  @GetMapping(value = "/from/{from}/to/{to}")
  CurrencyConversion getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to);
}
