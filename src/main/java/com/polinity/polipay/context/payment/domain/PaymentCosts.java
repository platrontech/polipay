package com.polinity.polipay.context.payment.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCosts {
    private BigDecimal price;
    private BigDecimal paidAmount;
    private BigDecimal polipayCommissionRate = BigDecimal.ZERO;
    private BigDecimal polipayCommissionCost = BigDecimal.ZERO;
    private BigDecimal paymentProviderCommissionRate = BigDecimal.ZERO;
    private BigDecimal paymentProviderCommissionCost = BigDecimal.ZERO;

    public BigDecimal getTotalCommissionRate() {
        return polipayCommissionRate.add(paymentProviderCommissionRate);
    }

    public BigDecimal getTotalCommissionCost() {
        return polipayCommissionCost.add(paymentProviderCommissionCost);
    }

    public BigDecimal getPaidAmount() {
        return paidAmount == null ? BigDecimal.ZERO : paidAmount;
    }

    public BigDecimal getPrice() {
        return price == null ? BigDecimal.ZERO : price;
    }
}
