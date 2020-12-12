package com.polinity.polipay.context.payment;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import com.polinity.polipay.context.payment.model.PaymentRequest;

public interface PaymentResultHandlerService {

    DoneResponse handle(PaymentRequest paymentRequest, BaseIparaResponse body);
}
