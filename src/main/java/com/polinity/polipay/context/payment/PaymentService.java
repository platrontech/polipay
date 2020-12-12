package com.polinity.polipay.context.payment;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.context.payment.model.PaymentRequest;

public interface PaymentService {

    DoneResponse authWithSavedCard(PaymentRequest paymentRequest);
}
