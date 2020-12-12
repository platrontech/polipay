package com.polinity.polipay.context.card.api.model;

import com.polinity.polipay.commons.api.model.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveCardRequest {
    private String alias;
    private String userId;
    private CreditCard creditCard;
}
