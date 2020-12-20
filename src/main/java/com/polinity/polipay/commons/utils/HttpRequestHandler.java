package com.polinity.polipay.commons.utils;

import com.polinity.polipay.commons.error.ApiException;
import com.polinity.polipay.commons.error.ErrorCodes;
import com.polinity.polipay.context.card.api.model.ipara.response.BaseIparaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

@Slf4j
public class HttpRequestHandler {

  public static <T extends BaseIparaResponse> T handle(Callable<ResponseEntity<T>> callable) {
    ResponseEntity<T> response;
    try {
      response = callable.call();
      handleHttpResult(response);

      return response.getBody();
    } catch (ApiException e) {
      throw e;
    } catch (Exception e) {
      throw new ApiException(ErrorCodes.API_SERVER_ERROR);
    }
  }

  private static void handleHttpResult(ResponseEntity<? extends BaseIparaResponse> responseEntity) {
    if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
      log.warn("service result is not valid with httpStatus={} ", responseEntity.getStatusCodeValue());
      throw new ApiException(ErrorCodes.API_SERVER_ERROR);
    }

    if (responseEntity.getBody().getErrorCode() != null) {
      log.warn("errorCode={} errorMessage=\"{}\"", responseEntity.getBody().getErrorCode(), responseEntity.getBody().getErrorMessage());
      throw new ApiException(IparaErrorCodeMapping.getErrorCodeByErrorCode(responseEntity.getBody().getErrorCode()));
    }
  }
}
