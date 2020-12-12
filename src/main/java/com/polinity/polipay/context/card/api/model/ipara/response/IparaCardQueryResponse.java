package com.polinity.polipay.context.card.api.model.ipara.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IparaCardQueryResponse extends BaseIparaResponse {

    List<BankCard> cards;
}
