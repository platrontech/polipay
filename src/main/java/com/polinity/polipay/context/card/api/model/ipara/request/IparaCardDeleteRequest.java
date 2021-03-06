package com.polinity.polipay.context.card.api.model.ipara.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IparaCardDeleteRequest extends BaseIparaRequest {

  String userId;
  String cardId;
  String clientIp;

  @Override
  protected String hashString() {
    return userId + cardId + clientIp;
  }
}
