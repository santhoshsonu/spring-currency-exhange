package com.sample.currencyexchange.service.impl;

import com.sample.currencyexchange.model.CurrencyExchange;
import com.sample.currencyexchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

  private static final String PORT_PROPERTY_KEY = "local.server.port";
  @Autowired Environment environment;

  @Override
  public CurrencyExchange getExchangeRate(String from, String to) {
    final String property = environment.getProperty(PORT_PROPERTY_KEY);
    return new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(80.50))
        .setEnvironment(property);
  }
}
