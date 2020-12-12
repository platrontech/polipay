package com.polinity.polipay.context.card;

import com.polinity.polipay.commons.api.model.BaseListResponse;
import com.polinity.polipay.context.card.api.model.CardTwin;

public interface CardQueryService {

    BaseListResponse<CardTwin> retrieveCreditCards(String userId);
}
