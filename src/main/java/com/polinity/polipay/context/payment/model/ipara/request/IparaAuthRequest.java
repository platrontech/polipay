package com.polinity.polipay.context.payment.model.ipara.request;

import com.polinity.polipay.context.card.api.model.ipara.request.BaseIparaRequest;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "auth")
@EqualsAndHashCode(callSuper = true)
public class IparaAuthRequest extends BaseIparaRequest {

  String threeD;
  String orderId;
  String amount;
  String installment;
  String userId;
  String cardId;
  String mode;

  IparaAuthPurchaser purchaser;
  List<IparaAuthProduct> products;

  @XmlElementWrapper
  @XmlElement(name="product")
  public List<IparaAuthProduct> getProducts() {
    return products;
  }

  @Override
  protected String hashString() {
    return orderId
        + amount
        + mode
        + userId
        + cardId
        + purchaser.getName()
        + purchaser.getSurname()
        + purchaser.getEmail();
  }
}
