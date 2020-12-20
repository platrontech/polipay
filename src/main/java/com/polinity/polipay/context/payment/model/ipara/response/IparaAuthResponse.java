package com.polinity.polipay.context.payment.model.ipara.response;

import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "authResponse")
public class IparaAuthResponse extends BaseIparaResponse {
  String amount;
  String orderId;
}
