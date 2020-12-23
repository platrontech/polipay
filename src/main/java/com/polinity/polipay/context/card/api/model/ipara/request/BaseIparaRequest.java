package com.polinity.polipay.context.card.api.model.ipara.request;

import com.polinity.polipay.commons.config.IparaConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseIparaRequest {

  @Setter
  String mode;

  protected abstract String hashString();

  public String getRequestHash() {
    return IparaConstants.PRIVATE_KEY + hashString() + getTransactionDateString();
  }

  private String getTransactionDateString() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(IparaConstants.TRANSACTION_DATE_FORMAT);
    return simpleDateFormat.format(new Date());
  }

}
