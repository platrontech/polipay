package com.polinity.polipay.commons.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
  private String cardHolderName;
  private String cardNumber;
  private String expireYear;
  private String expireMonth;
}
