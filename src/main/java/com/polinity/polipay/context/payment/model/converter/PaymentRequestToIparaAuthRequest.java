package com.polinity.polipay.context.payment.model.converter;

import com.polinity.polipay.context.payment.model.PaymentRequest;
import com.polinity.polipay.context.payment.model.ipara.request.IparaAuthProduct;
import com.polinity.polipay.context.payment.model.ipara.request.IparaAuthPurchaser;
import com.polinity.polipay.context.payment.model.ipara.request.IparaAuthRequest;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class PaymentRequestToIparaAuthRequest implements Converter<PaymentRequest, IparaAuthRequest> {

  @Override
  public IparaAuthRequest convert(PaymentRequest paymentRequest) {
    return IparaAuthRequest.builder()
        .orderId(paymentRequest.getOrderId())
        .purchaser(IparaAuthPurchaser.builder()
            .clientIp("127.0.0.1")
            .name(paymentRequest.getBuyer().getName())
            .surname(paymentRequest.getBuyer().getSurname())
            .email(paymentRequest.getBuyer().getEmail())
            .build())
        .userId(paymentRequest.getBuyer().getId())
        .cardId(paymentRequest.getCardId())
        .amount(convertBigDecimalToString(paymentRequest.getPrice()))
        .products(paymentRequest.getItems()
            .parallelStream()
            .map(item -> new IparaAuthProduct(item.getTitle(), convertBigDecimalToString(item.getPrice())))
            .collect(Collectors.toList()))
        .build();
  }

  private String convertBigDecimalToString(BigDecimal bigDecimal) {
    return bigDecimal.setScale(2).toString().replace(".", "");
  }
}
