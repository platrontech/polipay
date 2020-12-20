package com.polinity.polipay.context.payment.model.converter;

import com.polinity.polipay.context.payment.domain.MoneyTransfer;
import com.polinity.polipay.context.payment.domain.PaymentAuthDocument;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import org.springframework.core.convert.converter.Converter;

public class PaymentRequestToPaymentAuthDocument implements Converter<PaymentRequest, PaymentAuthDocument> {

  @Override
  public PaymentAuthDocument convert(PaymentRequest paymentRequest) {
    return PaymentAuthDocument.builder()
        .orderId(paymentRequest.getOrderId())
        .cardToken(paymentRequest.getCardId())
        .buyerId(paymentRequest.getBuyer().getId())
        .moneyTransfer(MoneyTransfer.builder()
            .iban(paymentRequest.getSeller().getIban())
            .sellerId(paymentRequest.getSeller().getId())
            .bankAccountHolderName(paymentRequest.getSeller().getBankAccountHolderName())
            .build())
        .build();
  }
}
