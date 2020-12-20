package com.polinity.polipay.context.card.impl;

import com.polinity.polipay.commons.api.model.BaseListResponse;
import com.polinity.polipay.commons.api.model.IparaEnvironment;
import com.polinity.polipay.commons.utils.HttpRequestHandler;
import com.polinity.polipay.commons.utils.IparaRequestHelper;
import com.polinity.polipay.context.card.CardQueryService;
import com.polinity.polipay.context.card.api.model.CardTwin;
import com.polinity.polipay.context.card.api.model.ipara.request.IparaCardQueryRequest;
import com.polinity.polipay.context.card.api.model.ipara.response.IparaCardQueryResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardQueryServiceImpl implements CardQueryService {

  private final IparaRequestHelper iparaRequestHelper;
  private final RestTemplate restTemplate;
  private final IparaEnvironment iparaEnvironment;

  @Override
  @SneakyThrows
  public BaseListResponse<CardTwin> retrieveCreditCards(String userId) {
    BaseListResponse<CardTwin> response = new BaseListResponse<>();
    IparaCardQueryRequest request = IparaCardQueryRequest.builder()
        .userId(userId)
        .clientIp("127.0.0.1")
        .build();
    request.setMode(iparaEnvironment.name());

    HttpEntity<IparaCardQueryRequest> httpEntity = new HttpEntity<>(request, iparaRequestHelper.getHttpHeadersForJson(request.getRequestHash()));
    IparaCardQueryResponse httpResponse = HttpRequestHandler.handle(() -> restTemplate.exchange("/bankcard/inquiry", HttpMethod.POST, httpEntity, IparaCardQueryResponse.class));

    httpResponse.getCards().stream()
        .map(card -> new CardTwin(card.getCardId(), card.getMaskNumber(), card.getBankName()))
        .forEach(response::append);

    return response;
  }
}
