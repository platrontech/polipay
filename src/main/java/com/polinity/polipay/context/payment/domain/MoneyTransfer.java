package com.polinity.polipay.context.payment.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyTransfer {

    private String id = UUID.randomUUID().toString();
    private String sellerId;
    private String iban;
    private String bankAccountHolderName;
    private BigDecimal transferredAmount = BigDecimal.ZERO;
    private LocalDateTime moneyTransferTime;
    private Boolean completed = Boolean.FALSE;
    private MoneyTransferStatus moneyTransferStatus = MoneyTransferStatus.WAITING;
}
