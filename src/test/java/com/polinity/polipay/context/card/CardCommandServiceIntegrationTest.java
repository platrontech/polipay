package com.polinity.polipay.context.card;

import com.polinity.polipay.BaseIntegrationTest;
import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.context.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardCommandServiceIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private CardCommandService cardCommandService;

  @Test
  public void saveCard() {
    DoneResponse doneResponse = saveTestCard();
    assertNotNull(doneResponse);
  }

  @Test
  public void deleteCard() {
    saveTestCard();
    String cardId = retrieveAllCards().getResults().get(0).getCardId();

    DoneResponse doneResponse = cardCommandService.deleteCard(TestData.USER_ID, cardId);
    assertNotNull(doneResponse);
  }
}