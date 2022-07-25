package com.sample.currencyconversion.service.Impl;

import com.sample.currencyconversion.model.CurrencyConversion;
import com.sample.currencyconversion.remote.CurrencyExchangeFeignClient;
import com.sample.currencyconversion.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

  @Autowired private CurrencyExchangeFeignClient remote;

  @Override
  public CurrencyConversion getConversion(String from, String to, BigDecimal quantity) {
    log.info("Calling Remote GET Currency Exchange rate API for {} to {}", from, to);
    final CurrencyConversion currencyConversion = remote.getCurrencyExchangeRate(from, to);
    return currencyConversion
        .setQuantity(quantity)
        .setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
  }

  public CurrencyConversion getConversionUsingRestTemplate(
      String from, String to, BigDecimal quantity) {
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
