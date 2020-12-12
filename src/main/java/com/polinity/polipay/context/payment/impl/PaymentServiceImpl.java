package com.polinity.polipay.context.payment.impl;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.commons.api.model.IparaEnvironment;
import com.polinity.polipay.commons.error.ApiException;
import com.polinity.polipay.commons.error.ErrorCodes;
import com.polinity.polipay.commons.utils.IparaHelper;
import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import com.polinity.polipay.context.payment.PaymentResultHandlerService;
import com.polinity.polipay.context.payment.PaymentService;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import com.polinity.polipay.context.payment.model.ipara.request.IparaAuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final String DEFAULT_INSTALLMENT = "1";

    private final IparaHelper iparaHelper;
    private final RestTemplate restTemplate;
    private final IparaEnvironment iparaEnvironment;
    private final ConversionService defaultConversionService;
    private final PaymentResultHandlerService paymentResultHandlerService;

    @SneakyThrows
    @Override
    public DoneResponse authWithSavedCard(PaymentRequest paymentRequest) {
        IparaAuthRequest authRequest = defaultConversionService.convert(paymentRequest, IparaAuthRequest.class);
        populateInitialAuthRequest(authRequest);

        HttpHeaders headers = iparaHelper.getHttpHeadersForXml();
        HttpEntity<IparaAuthRequest> httpEntity = new HttpEntity<>(authRequest, headers);
        ResponseEntity<BaseIparaResponse> responseEntity = restTemplate.exchange("/rest/payment/auth", HttpMethod.POST, httpEntity, BaseIparaResponse.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("error occurred while retrieving cards with httpStatus={}", responseEntity.getStatusCodeValue());
            throw new ApiException(ErrorCodes.GENERIC_PAYMENT_ERROR);
        }

        return paymentResultHandlerService.handle(paymentRequest, responseEntity.getBody());
    }

    private void populateInitialAuthRequest(IparaAuthRequest authRequest) {
        authRequest.setThreeD("false");
        authRequest.setInstallment(DEFAULT_INSTALLMENT);
        authRequest.setMode(iparaEnvironment.getValue());
    }
}
