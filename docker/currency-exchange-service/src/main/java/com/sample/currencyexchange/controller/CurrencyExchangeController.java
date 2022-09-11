package com.sample.currencyexchange.controller;

import com.sample.currencyexchange.model.CurrencyExchange;
import com.sample.currencyexchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/currency-exchange", produces = APPLICATION_JSON_VALUE)
public class CurrencyExchangeController {

  @Autowired CurrencyExchangeService currencyExchangeService;

  @GetMapping(path = "/from/{from}/to/{to}")
  public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
    return currencyExchangeService.getExchangeRate(from, to);
  }
}
