package com.polinity.polipay.context.card.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTwin {
    private String cardId;
    private String maskedCardNumber;
    private String bank;
}
