package com.polinity.polipay.context.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {

  @NotNull
  private String id;

  @NotNull
  private String name;

  @NotNull
  private String surname;

  @NotNull
  private String email;
}
