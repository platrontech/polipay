package com.polinity.polipay.context.card.api.model.converter;

import com.polinity.polipay.context.card.api.model.SaveCardRequest;
import com.polinity.polipay.context.card.api.model.ipara.request.IparaCardCreateRequest;
import org.springframework.core.convert.converter.Converter;

public class SaveCardRequestToIparaCardCreateRequest implements Converter<SaveCardRequest, IparaCardCreateRequest> {

  @Override
  public IparaCardCreateRequest convert(SaveCardRequest saveCardRequest) {
    return IparaCardCreateRequest.builder()
        .cardAlias(saveCardRequest.getAlias())
        .userId(saveCardRequest.getUserId())
        .cardOwnerName(saveCardRequest.getCreditCard().getCardHolderName())
        .cardNumber(saveCardRequest.getCreditCard().getCardNumber())
        .cardExpireMonth(saveCardRequest.getCreditCard().getExpireMonth())
        .cardExpireYear(saveCardRequest.getCreditCard().getExpireYear())
        .clientIp("127.0.0.1") // idare ediver :)
        .build();
  }
}
