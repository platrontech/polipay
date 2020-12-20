package com.polinity.polipay.context.payment.impl;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.commons.api.model.IparaEnvironment;
import com.polinity.polipay.commons.utils.HttpRequestHandler;
import com.polinity.polipay.commons.utils.IparaRequestHelper;
import com.polinity.polipay.context.payment.PaymentResultHandlerService;
import com.polinity.polipay.context.payment.PaymentService;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import com.polinity.polipay.context.payment.model.ipara.request.IparaAuthRequest;
import com.polinity.polipay.context.payment.model.ipara.response.IparaAuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private static final String DEFAULT_INSTALLMENT = "1";

  private final IparaRequestHelper iparaRequestHelper;
  private final RestTemplate restTemplate;
  private final IparaEnvironment iparaEnvironment;
  private final ConversionService mvcConversionService;
  private final PaymentResultHandlerService paymentResultHandlerService;

  @SneakyThrows
  @Override
  public DoneResponse authWithSavedCard(PaymentRequest paymentRequest) {
    IparaAuthRequest authRequest = mvcConversionService.convert(paymentRequest, IparaAuthRequest.class);
    populateInitialAuthRequest(authRequest);

    HttpHeaders headers = iparaRequestHelper.getHttpHeadersForXml(authRequest.getRequestHash());
    HttpEntity<IparaAuthRequest> httpEntity = new HttpEntity<>(authRequest, headers);

    IparaAuthResponse response = HttpRequestHandler.handle(() -> restTemplate.exchange("/rest/payment/auth", HttpMethod.POST, httpEntity, IparaAuthResponse.class));
    paymentResultHandlerService.handleSucceedPayment(response.getOrderId(), paymentRequest);

    return DoneResponse.of();
  }

  private void populateInitialAuthRequest(IparaAuthRequest authRequest) {
    authRequest.setThreeD("false");
    authRequest.setInstallment(DEFAULT_INSTALLMENT);
    authRequest.setMode(iparaEnvironment.name());
  }
}
