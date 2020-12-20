package com.polinity.polipay.context.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

  @NotNull
  String orderId;

  @NotNull
  String cardId;

  @Valid
  @NotNull
  Seller seller;

  @Valid
  @NotNull
  Buyer buyer;

  @DecimalMin("0.01")
  BigDecimal price;

  @NotEmpty
  @NotNull
  @Valid
  List<OrderItem> items;
}
