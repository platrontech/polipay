package com.polinity.polipay.context.payment.impl;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.commons.error.ApiException;
import com.polinity.polipay.commons.error.ErrorCodes;
import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import com.polinity.polipay.context.payment.PaymentResultHandlerService;
import com.polinity.polipay.context.payment.domain.*;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResultHandlerServiceImpl implements PaymentResultHandlerService {

    private static final String SUCCEED_PAYMENT = "1";
    private static final String NO_LIMIT_ERROR_CODE = "4";

    private final ConversionService defaultConversionService;
    private final PaymentAuthRepository paymentAuthRepository;
    private final PaymentAuthErrorRepository paymentAuthErrorRepository;

    @Override
    public DoneResponse handle(PaymentRequest request, BaseIparaResponse response) {
        if (SUCCEED_PAYMENT.equals(response.getResult())) {
            handleSucceedPayment(request);
            return DoneResponse.of();
        }

        handleFailedPayment(request, response);
        return null;
    }

    private void handleSucceedPayment(PaymentRequest paymentRequest) {
        PaymentAuthDocument paymentAuthDocument = defaultConversionService.convert(paymentRequest, PaymentAuthDocument.class);
        paymentAuthRepository.save(paymentAuthDocument);
    }

    private void handleFailedPayment(PaymentRequest paymentRequest, BaseIparaResponse response) {
        PaymentAuthErrorDocument paymentAuthErrorDocument = defaultConversionService.convert(paymentRequest, PaymentAuthErrorDocument.class);
        populatePaymentAuthErrorDocumentWithPaymentResult(response, paymentAuthErrorDocument);

        paymentAuthErrorRepository.save(paymentAuthErrorDocument);
        handleErrorCode(response.getErrorCode());
    }

    private void handleErrorCode(String errorCode) {
        if (NO_LIMIT_ERROR_CODE.equals(errorCode)) {
            throw new ApiException(ErrorCodes.PAYMENT_CARD_LIMIT_ERROR);
        }

        throw new ApiException(ErrorCodes.GENERIC_PAYMENT_ERROR);
    }

    private void populatePaymentAuthErrorDocumentWithPaymentResult(BaseIparaResponse response, PaymentAuthErrorDocument paymentAuthErrorDocument) {
        paymentAuthErrorDocument.setErrorCode(response.getErrorCode());
        paymentAuthErrorDocument.setErrorMessage(response.getErrorMessage());
    }
}
