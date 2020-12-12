package com.polinity.polipay.context.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @NotNull
    private String id;

    @NotNull
    private String iban;

    @NotNull
    private String bankAccountHolderName;
}
