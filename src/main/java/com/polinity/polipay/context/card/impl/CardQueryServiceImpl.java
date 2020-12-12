package com.polinity.polipay.context.card.impl;

import com.polinity.polipay.commons.api.model.BaseListResponse;
import com.polinity.polipay.commons.api.model.IparaEnvironment;
import com.polinity.polipay.commons.utils.IparaHelper;
import com.polinity.polipay.context.card.CardQueryService;
import com.polinity.polipay.context.card.api.model.CardTwin;
import com.polinity.polipay.context.card.api.model.ipara.request.IparaCardQueryRequest;
import com.polinity.polipay.context.card.api.model.ipara.response.IparaCardQueryResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardQueryServiceImpl implements CardQueryService {

    private final IparaHelper iparaHelper;
    private final RestTemplate restTemplate;
    private final IparaEnvironment iparaEnvironment;

    @Override
    @SneakyThrows
    public BaseListResponse<CardTwin> retrieveCreditCards(String userId) {
        BaseListResponse<CardTwin> response = new BaseListResponse<>();
        IparaCardQueryRequest.builder()
                .mode(iparaEnvironment.getValue())
                .userId(userId);

        HttpEntity<String> httpEntity = new HttpEntity<>("", iparaHelper.getHttpHeadersForJson());
        ResponseEntity<IparaCardQueryResponse> responseEntity = restTemplate.exchange("/bankcard/inquiry", HttpMethod.POST, httpEntity, IparaCardQueryResponse.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.warn("error occurred while retrieving cards with httpStatus={}", responseEntity.getStatusCodeValue());
            return response;
        }

        responseEntity.getBody().getCards().stream()
                .map(card -> new CardTwin(card.getCardId(), card.getMaskNumber(), card.getBankName()))
                .forEach(response::append);

        return response;
    }
}
