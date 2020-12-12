package com.polinity.polipay.context.payment.model.converter;

import com.polinity.polipay.context.payment.domain.PaymentAuthErrorDocument;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import org.springframework.core.convert.converter.Converter;

public class PaymentRequestToPaymentAuthErrorDocument implements Converter<PaymentRequest, PaymentAuthErrorDocument> {

    @Override
    public PaymentAuthErrorDocument convert(PaymentRequest paymentRequest) {
        return PaymentAuthErrorDocument.builder()
                .orderId(paymentRequest.getOrderId())
                .buyerId(paymentRequest.getBuyerId())
                .sellerId(paymentRequest.getSeller().getId())
                .cardToken(paymentRequest.getCardId())
                .price(paymentRequest.getPrice())
                .build();
    }
}
