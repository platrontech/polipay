package com.polinity.polipay.context;

import com.polinity.polipay.commons.api.model.CreditCard;

public class TestData {
  public static final String USER_ID = "1234";
  public static final CreditCard TEST_CARD = CreditCard.builder()
      .cardHolderName("hasan")
      .cardNumber("4282209027132016")
      .expireMonth("12")
      .expireYear("24")
      .build();
}
