package com.polinity.polipay.context.card.api.model.ipara.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IparaCardCreateRequest extends BaseIparaRequest {

  String userId;
  String cardOwnerName;
  String cardNumber;
  String cardAlias;
  String cardExpireMonth;
  String cardExpireYear;
  String clientIp;
  String mode;

  @Override
  protected String hashString() {
    return userId
        + cardOwnerName
        + cardNumber
        + cardExpireMonth
        + cardExpireYear
        + clientIp;
  }
}
