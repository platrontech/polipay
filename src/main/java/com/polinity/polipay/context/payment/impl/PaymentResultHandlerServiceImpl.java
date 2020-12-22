package com.polinity.polipay.context.payment.impl;

import com.polinity.polipay.commons.error.ApiException;
import com.polinity.polipay.context.payment.PaymentResultHandlerService;
import com.polinity.polipay.context.payment.domain.PaymentAuthDocument;
import com.polinity.polipay.context.payment.domain.PaymentAuthErrorDocument;
import com.polinity.polipay.context.payment.domain.PaymentAuthErrorRepository;
import com.polinity.polipay.context.payment.domain.PaymentAuthRepository;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResultHandlerServiceImpl implements PaymentResultHandlerService {

  private final ConversionService mvcConversionService;
  private final PaymentAuthRepository paymentAuthRepository;
  private final PaymentAuthErrorRepository paymentAuthErrorRepository;

  public void handleSucceedPayment(String paymentId, PaymentRequest paymentRequest) {
    PaymentAuthDocument paymentAuthDocument = mvcConversionService.convert(paymentRequest, PaymentAuthDocument.class);
    paymentAuthDocument.setPaymentId(paymentId);

    paymentAuthRepository.save(paymentAuthDocument);
  }

  public void handleFailedPayment(PaymentRequest paymentRequest, ApiException error) {
    PaymentAuthErrorDocument paymentAuthErrorDocument = mvcConversionService.convert(paymentRequest, PaymentAuthErrorDocument.class);
    populatePaymentAuthErrorDocumentWithPaymentResult(paymentAuthErrorDocument, error);
    paymentAuthErrorRepository.save(paymentAuthErrorDocument);
  }


  private void populatePaymentAuthErrorDocumentWithPaymentResult(PaymentAuthErrorDocument paymentAuthErrorDocument, ApiException error) {
    if (error.getExternalError() != null) {
      paymentAuthErrorDocument.setErrorCode(error.getExternalError().getCode());
      paymentAuthErrorDocument.setErrorMessage(error.getExternalError().getMessage());
    } else {
      paymentAuthErrorDocument.setErrorCode(String.valueOf(error.getErrorCode().getError()));
      paymentAuthErrorDocument.setErrorMessage(error.getErrorCode().getError());
    }
  }
}
