package com.polinity.polipay.context.card.impl;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.commons.api.model.IparaEnvironment;
import com.polinity.polipay.commons.error.ApiException;
import com.polinity.polipay.commons.error.ErrorCodes;
import com.polinity.polipay.commons.utils.IparaHelper;
import com.polinity.polipay.context.card.CardCommandService;
import com.polinity.polipay.context.card.api.model.SaveCardRequest;
import com.polinity.polipay.context.card.api.model.ipara.request.IparaCardCreateRequest;
import com.polinity.polipay.context.card.api.model.ipara.request.IparaCardDeleteRequest;
import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import com.polinity.polipay.context.card.api.model.ipara.response.IparaCardQueryResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardCommandServiceImpl implements CardCommandService {

    private final IparaHelper iparaHelper;
    private final RestTemplate restTemplate;
    private final IparaEnvironment iparaEnvironment;
    private final ConversionService defaultConversionService;

    @Override
    @SneakyThrows
    public DoneResponse saveCard(SaveCardRequest saveCardRequest) {
        IparaCardCreateRequest request = defaultConversionService.convert(saveCardRequest, IparaCardCreateRequest.class);
        request.setMode(iparaEnvironment.getValue());

        HttpEntity<IparaCardCreateRequest> httpEntity = new HttpEntity<>(request, iparaHelper.getHttpHeadersForJson());

        ResponseEntity<IparaCardQueryResponse> responseEntity = restTemplate.exchange("/bankcard/create", HttpMethod.POST, httpEntity, IparaCardQueryResponse.class);
        handleHttpResult(responseEntity);

        return DoneResponse.of();
    }

    private <T> void handleHttpResult(ResponseEntity<T> responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("error occurred while retrieving cards with httpStatus={}", responseEntity.getStatusCodeValue());
            throw new ApiException(ErrorCodes.CARD_ERROR);
        }
    }

    @Override
    @SneakyThrows
    public DoneResponse deleteCard(String userId, String cardId) {
        IparaCardDeleteRequest request = IparaCardDeleteRequest.builder()
                .userId(userId)
                .cardId(cardId)
                .mode(iparaEnvironment.getValue())
                .build();

        HttpEntity<IparaCardDeleteRequest> httpEntity = new HttpEntity<>(request, iparaHelper.getHttpHeadersForJson());
        ResponseEntity<BaseIparaResponse> responseEntity = restTemplate.exchange("/bankcard/delete", HttpMethod.POST, httpEntity, BaseIparaResponse.class);
        handleHttpResult(responseEntity);

        return DoneResponse.of();
    }
}
