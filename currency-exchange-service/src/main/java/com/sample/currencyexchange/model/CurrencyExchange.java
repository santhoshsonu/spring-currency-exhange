package com.sample.currencyexchange.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {
  @Id
  @SequenceGenerator(
      name = "currency_exchange_seq",
      sequenceName = "currency_exchange_seq",
      allocationSize = 1)
  @GeneratedValue(generator = "currency_exchange_seq", strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  @Column(name = "currency_from", nullable = false)
  private String from;

  @Column(name = "currency_to", nullable = false)
  private String to;

  @Column(name = "conversion_multiple", nullable = false)
  private BigDecimal conversionMultiple;

  @Transient private String environment;

  public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.conversionMultiple = conversionMultiple;
  }
}
