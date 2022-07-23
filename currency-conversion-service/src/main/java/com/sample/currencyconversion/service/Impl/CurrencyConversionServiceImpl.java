package com.sample.currencyconversion.service.Impl;

import com.sample.currencyconversion.model.CurrencyConversion;
import com.sample.currencyconversion.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

  private static final String PORT_PROPERTY_FIELD = "local.server.port";

  @Autowired private Environment environment;

  @Override
  public CurrencyConversion getConversion(String from, String to, BigDecimal quantity) {
    final Map<String, String> uriVariables =
        Map.ofEntries(Map.entry("from", from), Map.entry("to", to));

    ResponseEntity<CurrencyConversion> entity =
        new RestTemplate()
            .getForEntity(
                "http://localhost:8000/api/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables);
    if (entity.getStatusCode().is2xxSuccessful()) {
      CurrencyConversion currencyConversion = entity.getBody();
      if (nonNull(currencyConversion)) {
        return currencyConversion.setTotalCalculatedAmount(
            quantity.multiply(currencyConversion.getConversionMultiple()));
      }
    }
    throw new RuntimeException(
        String.format("Unable to calculate conversion from %s to %s", from, to));
  }
}
