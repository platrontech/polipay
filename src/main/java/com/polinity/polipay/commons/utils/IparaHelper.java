package com.polinity.polipay.commons.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class IparaHelper {

    private static final String DIGEST_ALGORITHM = "SHA1";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Value("${provider.ipara.security.hashString}")
    private String hashString;
    @Value("${provider.ipara.security.publicKey}")
    private String publicKey;
    @Value("${provider.ipara.api.version}")
    private String version;

    public String computeHash(String hashString) throws Exception {
        MessageDigest sha1 = MessageDigest.getInstance(DIGEST_ALGORITHM);
        return DatatypeConverter.printBase64Binary(sha1.digest(hashString.getBytes(StandardCharsets.UTF_8)));
    }

    @SneakyThrows
    public String createToken(String publicKey, String hashString) {
        MessageDigest sha1 = MessageDigest.getInstance(DIGEST_ALGORITHM);
        String hash = DatatypeConverter.printBase64Binary((sha1.digest(hashString.getBytes(StandardCharsets.UTF_8))));
        return String.format("%s:%s", publicKey, hash);
    }

    public HttpHeaders getHttpHeadersForJson() {
        return getHttpHeaders("application/json;charset=UTF-8");
    }

    public HttpHeaders getHttpHeadersForXml() {
        return getHttpHeaders("application/xml;charset=UTF-8");
    }

    private HttpHeaders getHttpHeaders(String acceptType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", acceptType);
        headers.set("version", version);
        headers.set("token", createToken(publicKey, hashString));
        headers.set("transactionDate", getTransactionDateString());

        return headers;
    }

    public String getTransactionDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        return simpleDateFormat.format(new Date());
    }
}

