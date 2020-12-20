package com.polinity.polipay.context.card.impl;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.commons.api.model.IparaEnvironment;
import com.polinity.polipay.commons.utils.HttpRequestHandler;
import com.polinity.polipay.commons.utils.IparaRequestHelper;
import com.polinity.polipay.context.card.CardCommandService;
import com.polinity.polipay.context.card.api.model.SaveCardRequest;
import com.polinity.polipay.context.card.api.model.ipara.request.IparaCardCreateRequest;
import com.polinity.polipay.context.card.api.model.ipara.request.IparaCardDeleteRequest;
import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardCommandServiceImpl implements CardCommandService {

  private final IparaRequestHelper iparaRequestHelper;
  private final RestTemplate restTemplate;
  private final IparaEnvironment iparaEnvironment;
  private final ConversionService mvcConversionService;

  @Override
  public DoneResponse saveCard(SaveCardRequest saveCardRequest) {
    IparaCardCreateRequest request = mvcConversionService.convert(saveCardRequest, IparaCardCreateRequest.class);
    request.setMode(iparaEnvironment.name());

    HttpEntity<IparaCardCreateRequest> httpEntity = new HttpEntity<>(request, iparaRequestHelper.getHttpHeadersForJson(request.getRequestHash()));
    HttpRequestHandler.handle(() -> restTemplate.exchange("/bankcard/create", HttpMethod.POST, httpEntity, BaseIparaResponse.class));

    return DoneResponse.of();
  }

  @Override
  public DoneResponse deleteCard(String userId, String cardId) {
    IparaCardDeleteRequest request = IparaCardDeleteRequest.builder()
        .userId(userId)
        .cardId(cardId)
        .clientIp("127.0.0.1")
        .build();
    request.setMode(iparaEnvironment.name());

    HttpEntity<IparaCardDeleteRequest> httpEntity = new HttpEntity<>(request, iparaRequestHelper.getHttpHeadersForJson(request.getRequestHash()));
    HttpRequestHandler.handle(() -> restTemplate.exchange("/bankcard/delete", HttpMethod.POST, httpEntity, BaseIparaResponse.class));

    return DoneResponse.of();
  }
}
