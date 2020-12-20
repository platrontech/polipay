package com.polinity.polipay.commons.api.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class DoneResponse extends BaseResponse {

  public static DoneResponse of() {
    return DoneResponse.builder().build();
  }
}
