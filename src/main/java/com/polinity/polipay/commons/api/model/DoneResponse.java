package com.polinity.polipay.commons.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoneResponse extends BaseResponse {

  public static DoneResponse of() {
    return DoneResponse.builder().build();
  }
}
