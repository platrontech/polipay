package com.polinity.polipay.commons.error;

public class DomainException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String message;
  private final ErrorCodes errorCode;

  public DomainException(String message, ErrorCodes errorCode) {
    this.message = message;
    this.errorCode = errorCode;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public ErrorCodes getErrorCode() {
    return errorCode;
  }
}