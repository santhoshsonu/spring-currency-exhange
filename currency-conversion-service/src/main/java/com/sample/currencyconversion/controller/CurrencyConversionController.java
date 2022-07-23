package com.sample.currencyconversion.controller;

import com.sample.currencyconversion.model.CurrencyConversion;
import com.sample.currencyconversion.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/currency-conversion")
public class CurrencyConversionController {

  @Autowired private CurrencyConversionService currencyConversionService;

  @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion getCurrencyConversion(
      @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
    return currencyConversionService.getConversion(from, to, quantity);
  }
}
