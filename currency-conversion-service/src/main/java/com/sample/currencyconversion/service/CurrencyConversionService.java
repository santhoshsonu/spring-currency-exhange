package com.sample.currencyconversion.service;

import com.sample.currencyconversion.model.CurrencyConversion;

import java.math.BigDecimal;

public interface CurrencyConversionService {
  CurrencyConversion getConversion(String from, String to, BigDecimal quantity);
}
