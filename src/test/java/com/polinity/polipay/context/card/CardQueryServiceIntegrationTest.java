package com.polinity.polipay.context.card;

import com.polinity.polipay.BaseIntegrationTest;
import com.polinity.polipay.commons.api.model.BaseListResponse;
import com.polinity.polipay.context.card.api.model.CardTwin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardQueryServiceIntegrationTest extends BaseIntegrationTest {

  @Test
  public void retrieveCreditCards() {
    saveTestCard();
    BaseListResponse<CardTwin> response = retrieveAllCards();
    assertNotNull(response.getResults());
    assertTrue(response.getResults().size() > 0);
  }
}