package com.polinity.polipay.context.card.api.model.ipara.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IparaCardQueryRequest extends BaseIparaRequest {

  String userId;
  String clientIp;

  @Override
  protected String hashString() {
    return userId + clientIp;
  }
}
