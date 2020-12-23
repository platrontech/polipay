package com.polinity.polipay.commons.utils;

import com.polinity.polipay.commons.error.ErrorCodes;

import java.util.HashMap;
import java.util.Map;

public class IparaErrorCodeMapping {
  private static final Map<String, ErrorCodes> errorCodes = new HashMap<>();

  static {
    errorCodes.put("970", ErrorCodes.CARD_NOT_FOUND);
    errorCodes.put("973", ErrorCodes.CARD_ALREADY_REGISTERED);
    errorCodes.put("989", ErrorCodes.CARD_ALREADY_REGISTERED);
    errorCodes.put("877", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("834", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("831", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("828", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("827", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("826", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("824", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("823", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("822", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("821", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("820", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("17", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("15", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("7", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("6", ErrorCodes.CARD_VALIDATION_ERROR);
    errorCodes.put("5", ErrorCodes.CARD_VALIDATION_ERROR);
  }

  public static ErrorCodes getErrorCodeByErrorCode(String errorCode) {
    if (errorCodes.containsKey(errorCode)) {
      return errorCodes.get(errorCode);
    }

    return ErrorCodes.GENERIC_ERROR;
  }
}
