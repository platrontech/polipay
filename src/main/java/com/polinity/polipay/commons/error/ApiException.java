package com.polinity.polipay.commons.error;

public class ApiException extends RuntimeException {

  private static final long serialVersionUID = -2807615315787903932L;

  private final ErrorCodes errorCode;
  private String message;
  private ExternalError externalError;

  public ApiException(ErrorCodes errorCode) {
    this.errorCode = errorCode;
  }

  public ApiException(ErrorCodes errorCode, ExternalError externalError) {
    this.errorCode = errorCode;
    this.externalError = externalError;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public ErrorCodes getErrorCode() {
    return errorCode;
  }

  public ExternalError getExternalError() {
    return externalError;
  }
}