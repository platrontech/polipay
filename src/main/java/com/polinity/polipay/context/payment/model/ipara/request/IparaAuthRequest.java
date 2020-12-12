package com.polinity.polipay.context.payment.model.ipara.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "auth")
public class IparaAuthRequest {

    @XmlElement(name = "threeD")
    String threeD;

    @XmlElement(name = "orderId")
    String orderId;

    @XmlElement(name = "amount")
    String amount;

    @XmlElement(name = "installment")
    String installment;

    @XmlElement(name = "userId")
    String userId;

    @XmlElement(name = "cardId")
    String cardId;

    String mode;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    List<IparaAuthProduct> products;
}
