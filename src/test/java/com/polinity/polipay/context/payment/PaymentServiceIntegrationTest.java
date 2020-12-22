package com.polinity.polipay.context.payment;

import com.polinity.polipay.BaseIntegrationTest;
import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.context.TestData;
import com.polinity.polipay.context.payment.model.Buyer;
import com.polinity.polipay.context.payment.model.OrderItem;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import com.polinity.polipay.context.payment.model.Seller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentServiceIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private PaymentService paymentService;

  @Test
  public void authWithSavedCard() {
    saveTestCard();
    String cardId = retrieveAllCards().getResults().get(0).getCardId();

    PaymentRequest paymentRequest = PaymentRequest.builder()
        .cardId(cardId)
        .orderId("ORDER" + System.currentTimeMillis())
        .buyer(Buyer.builder()
            .id(TestData.USER_ID)
            .name("omer")
            .surname("taskin")
            .email("a@b.com")
            .build())
        .price(BigDecimal.TEN)
        .items(Collections.singletonList(OrderItem.builder()
            .price(BigDecimal.TEN)
            .title("Test")
            .build()))
        .seller(Seller.builder()
            .bankAccountHolderName("abcd")
            .iban("TR123")
            .build())
        .build();

    DoneResponse response = paymentService.authWithSavedCard(paymentRequest);
    assertNotNull(response);
  }

}