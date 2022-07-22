package com.sample.currencyexchange.service.impl;

import com.sample.currencyexchange.model.CurrencyExchange;
import com.sample.currencyexchange.service.CurrencyExchangeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

  @Override
  public CurrencyExchange getExchangeRate(String from, String to) {
    return new CurrencyExchange()
        .setId(1000L)
        .setFrom(from)
        .setTo(to)
        .setConversionMultiple(BigDecimal.valueOf(80.50));
  }
}
