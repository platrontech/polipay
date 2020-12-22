package com.polinity.polipay.context.payment;

import com.polinity.polipay.commons.error.ApiException;
import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import com.polinity.polipay.context.payment.model.PaymentRequest;

public interface PaymentResultHandlerService {

  void handleSucceedPayment(String orderId, PaymentRequest paymentRequest);

  void handleFailedPayment(PaymentRequest paymentRequest, ApiException error);
}
