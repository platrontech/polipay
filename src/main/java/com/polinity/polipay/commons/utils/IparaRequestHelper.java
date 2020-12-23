package com.polinity.polipay.commons.utils;

import com.polinity.polipay.commons.config.IparaConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class IparaRequestHelper {

  private static final String DIGEST_ALGORITHM = "SHA1";

  @Value("${provider.ipara.security.publicKey}")
  private String publicKey;
  @Value("${provider.ipara.api.version}")
  private String version;

  public static String getTransactionDateString() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(IparaConstants.TRANSACTION_DATE_FORMAT);
    return simpleDateFormat.format(new Date());
  }

  public String computeHash(String hashString) throws Exception {
    MessageDigest sha1 = MessageDigest.getInstance(DIGEST_ALGORITHM);
    return DatatypeConverter.printBase64Binary(sha1.digest(hashString.getBytes(StandardCharsets.UTF_8)));
  }

  @SneakyThrows
  public String createToken(String hashString) {
    return String.format("%s:%s", publicKey, computeHash(hashString));
  }

  public HttpHeaders getHttpHeadersForJson(String hashString) {
    return getHttpHeaders("application/json;charset=UTF-8", hashString);
  }

  public HttpHeaders getHttpHeadersForXml(String hashString) {
    return getHttpHeaders("application/xml;charset=UTF-8", hashString);
  }

  private HttpHeaders getHttpHeaders(String acceptType, String hashString) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", acceptType);
    headers.set("version", version);
    headers.set("token", createToken(hashString));
    headers.set("transactionDate", getTransactionDateString());

    return headers;
  }
}

