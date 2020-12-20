package com.polinity.polipay.context.payment.domain;

import com.polinity.polipay.commons.persistence.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@Document("payment_auth")
public class PaymentAuthDocument extends BaseDocument {
    private String paymentId;
    private String orderId;
    private String buyerId;
    private String cardToken;
    private PaymentType paymentType = PaymentType.ORDER;
    private MoneyTransfer moneyTransfer = new MoneyTransfer();
    private PaymentSource paymentSource = PaymentSource.TANBULA;
    private PaymentGateway paymentGateway = PaymentGateway.IPARA;
    private PaymentCosts paymentCosts = new PaymentCosts();
}
