package com.polinity.polipay.context.card.api.model.ipara.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IparaCardCreateRequest {

    String userId;
    String cardOwnerName;
    String cardNumber;
    String cardAlias;
    String cardExpireMonth;
    String cardExpireYear;
    String clientIp;
    String mode;
}
