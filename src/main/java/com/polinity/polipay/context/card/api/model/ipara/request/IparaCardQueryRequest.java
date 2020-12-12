package com.polinity.polipay.context.card.api.model.ipara.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IparaCardQueryRequest {

    String userId;
    String clientIp;
    String cardId;
    String mode;
}
