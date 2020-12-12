package com.polinity.polipay.context.card.api.model.ipara.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseIparaResponse {

    @XmlElement(name = "result")
    String result;

    @XmlElement(name = "errorCode")
    String errorCode;

    @XmlElement(name = "errorMessage")
    String errorMessage;

    @XmlElement(name = "responseMessage")
    String responseMessage;

    @XmlElement(name = "mode")
    String mode;

    @XmlElement(name = "hash")
    String hash;

}
