package com.polinity.polipay.context.card.api.model.ipara.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankCardCreateResponse {

    public String cardId;
    public String maskNumber;
    public String bankName;
}
