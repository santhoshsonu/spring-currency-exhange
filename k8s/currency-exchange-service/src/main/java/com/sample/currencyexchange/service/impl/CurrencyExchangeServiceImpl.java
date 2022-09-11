package com.sample.currencyexchange.service.impl;

import com.sample.currencyexchange.model.CurrencyExchange;
import com.sample.currencyexchange.repository.CurrencyExchangeRepository;
import com.sample.currencyexchange.service.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@Slf4j
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

  private static final String PORT_PROPERTY_KEY = "local.server.port";
  private static final String NOT_FOUND_EXCEPTION = "Could not find Exchange Rate from %s to %s";

  @Autowired private Environment environment;

  @Autowired private CurrencyExchangeRepository repository;

  @Override
  public CurrencyExchange getExchangeRate(String from, String to) {
    log.info("Get Exchange Rate from {} to {}", from, to);
    final String port = environment.getProperty(PORT_PROPERTY_KEY);
    if (nonNull(from) && nonNull(to) && from.equals(to)) {
      return new CurrencyExchange()
          .setConversionMultiple(BigDecimal.valueOf(1.0d))
          .setFrom(from)
          .setTo(to)
          .setEnvironment(port);
    }
    return repository
        .findByFromAndTo(from, to)
        .map(currencyExchange -> currencyExchange.setEnvironment(port))
        .orElseThrow(() -> new RuntimeException(String.format(NOT_FOUND_EXCEPTION, from, to)));
  }
}
