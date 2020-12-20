package com.polinity.polipay;

import com.polinity.polipay.commons.api.model.BaseListResponse;
import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.commons.config.PolipayConfig;
import com.polinity.polipay.context.TestData;
import com.polinity.polipay.context.card.CardCommandService;
import com.polinity.polipay.context.card.CardQueryService;
import com.polinity.polipay.context.card.api.model.CardTwin;
import com.polinity.polipay.context.card.api.model.SaveCardRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
@SpringBootConfiguration
@ImportAutoConfiguration(PolipayConfig.class)
public abstract class BaseIntegrationTest {

  @Autowired
  private CardCommandService cardCommandService;

  @Autowired
  private CardQueryService cardQueryService;

  @BeforeEach
  public void setUp() {
    deleteAllCards();
  }

  protected void deleteAllCards() {
    List<CardTwin> cards = retrieveAllCards().getResults();
    if (CollectionUtils.isEmpty(cards)) return;

    cards.forEach(card -> deleteSingleCard(card.getCardId()));
  }

  protected DoneResponse deleteSingleCard(String cardId) {
    return cardCommandService.deleteCard(TestData.USER_ID, cardId);
  }

  protected BaseListResponse<CardTwin> retrieveAllCards() {
    return cardQueryService.retrieveCreditCards(TestData.USER_ID);
  }

  @Test
  protected DoneResponse saveTestCard() {
    SaveCardRequest request = SaveCardRequest.builder()
        .alias(TestData.TEST_CARD.getCardHolderName())
        .userId(TestData.USER_ID)
        .creditCard(TestData.TEST_CARD)
        .build();

    return cardCommandService.saveCard(request);
  }
}
