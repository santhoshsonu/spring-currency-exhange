package com.sample.currencyexchange.service;

import com.sample.currencyexchange.model.CurrencyExchange;

public interface CurrencyExchangeService {
  CurrencyExchange getExchangeRate(String from, String to);
}
