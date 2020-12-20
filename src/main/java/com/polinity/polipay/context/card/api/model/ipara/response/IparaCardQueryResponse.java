package com.polinity.polipay.context.card.api.model.ipara.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IparaCardQueryResponse extends BaseIparaResponse {

  List<BankCard> cards;

  @Override
  public String toString() {
    return super.toString();
  }
}
