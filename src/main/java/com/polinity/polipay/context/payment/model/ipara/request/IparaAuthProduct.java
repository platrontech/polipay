package com.polinity.polipay.context.payment.model.ipara.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "product")
public class IparaAuthProduct {

    @XmlElement(name = "productName")
    String title;

    @XmlElement(name = "price")
    String price;
}
