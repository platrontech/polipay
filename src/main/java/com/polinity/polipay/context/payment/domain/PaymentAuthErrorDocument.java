package com.polinity.polipay.context.payment.domain;

import com.polinity.polipay.commons.persistence.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@Document("payment_auth_error")
public class PaymentAuthErrorDocument extends BaseDocument {
    private String errorCode;
    private String errorMessage;
    private String orderId;
    private String buyerId;
    private String sellerId;
    private String cardToken;
    private BigDecimal price;
    private PaymentType paymentType = PaymentType.ORDER;
    private PaymentSource paymentSource = PaymentSource.TANBULA;
    private PaymentGateway paymentGateway = PaymentGateway.IPARA;
}
