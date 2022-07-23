package com.sample.currencyconversion.service.Impl;

import com.sample.currencyconversion.model.CurrencyConversion;
import com.sample.currencyconversion.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

  private static final String PORT_PROPERTY_FIELD = "local.server.port";

  @Autowired private Environment environment;

  @Override
  public CurrencyConversion getConversion(String from, String to, BigDecimal quantity) {
    final String port = environment.getProperty(PORT_PROPERTY_FIELD);

    final BigDecimal conversionMultiple = BigDecimal.valueOf(1.0);
    final BigDecimal totalCalculatedAmount = quantity.multiply(conversionMultiple);

    return new CurrencyConversion()
        .setId(1L)
        .setFrom(from)
        .setTo(to)
        .setQuantity(quantity)
        .setConversionMultiple(conversionMultiple)
        .setTotalCalculatedAmount(totalCalculatedAmount)
        .setEnvironment(port);
  }
}
