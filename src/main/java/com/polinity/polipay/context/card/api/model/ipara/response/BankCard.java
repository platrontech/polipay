package com.polinity.polipay.context.card.api.model.ipara.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankCard {
    String cardId;
    String maskNumber;
    String alias;
    String bankId;
    String bankName;
    String cardFamilyName;
    String serviceProvider;
    String threeDSecureMandatory;
    String cvcMandatory;

}
