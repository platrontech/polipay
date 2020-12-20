package com.polinity.polipay.context.card.api.model.ipara.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseIparaResponse {
  String result;
  String errorCode;
  String errorMessage;
  String responseMessage;
  String mode;
  String hash;

  @Override
  public String toString() {
    return "BaseIparaResponse{" +
        "result='" + result + '\'' +
        ", errorCode='" + errorCode + '\'' +
        ", errorMessage='" + errorMessage + '\'' +
        ", responseMessage='" + responseMessage + '\'' +
        '}';
  }
}
