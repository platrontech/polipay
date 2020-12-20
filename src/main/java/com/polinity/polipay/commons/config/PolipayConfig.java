package com.polinity.polipay.commons.config;

import com.polinity.polipay.commons.api.model.IparaEnvironment;
import com.polinity.polipay.context.card.api.model.converter.SaveCardRequestToIparaCardCreateRequest;
import com.polinity.polipay.context.payment.model.converter.PaymentRequestToIparaAuthRequest;
import com.polinity.polipay.context.payment.model.converter.PaymentRequestToPaymentAuthDocument;
import com.polinity.polipay.context.payment.model.converter.PaymentRequestToPaymentAuthErrorDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class PolipayConfig implements WebMvcConfigurer {

  @Value("${provider.ipara.connection.url}")
  private String url;

  @Value("${provider.ipara.connection.timeout}")
  private Long timeout;

  @Value("${provider.ipara.api.mode}")
  private String mode;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder()
        .rootUri(url)
        .setConnectTimeout(Duration.ofMillis(timeout))
        .setReadTimeout(Duration.ofMillis(timeout))
        .build();
  }

  @Bean
  public IparaEnvironment getEnvironment() {
    return IparaEnvironment.valueOf(mode);
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {

    registry.addConverter(new PaymentRequestToIparaAuthRequest());
    registry.addConverter(new PaymentRequestToPaymentAuthDocument());
    registry.addConverter(new PaymentRequestToPaymentAuthErrorDocument());
    registry.addConverter(new SaveCardRequestToIparaCardCreateRequest());
  }
}
