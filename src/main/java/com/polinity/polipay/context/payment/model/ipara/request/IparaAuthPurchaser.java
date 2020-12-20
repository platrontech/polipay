package com.polinity.polipay.context.payment.model.ipara.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "purchaser")
public class IparaAuthPurchaser {
  String name;
  String surname;
  String email;
  String clientIp;
}
