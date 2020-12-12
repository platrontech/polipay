package com.polinity.polipay.context.card;

import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.context.card.api.model.SaveCardRequest;

public interface CardCommandService {

    DoneResponse saveCard(SaveCardRequest saveCardRequest);

    DoneResponse deleteCard(String userId, String cardId);
}
