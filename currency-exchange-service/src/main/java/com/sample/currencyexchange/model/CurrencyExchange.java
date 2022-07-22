package com.sample.currencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CurrencyExchange {

  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;
}
