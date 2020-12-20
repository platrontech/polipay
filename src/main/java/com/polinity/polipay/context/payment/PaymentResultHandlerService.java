package com.polinity.polipay.context.payment;

import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import com.polinity.polipay.context.payment.model.PaymentRequest;

public interface PaymentResultHandlerService {

  void handleFailedPayment(PaymentRequest paymentRequest, BaseIparaResponse response);

  void handleSucceedPayment(String orderId, PaymentRequest paymentRequest);
}
