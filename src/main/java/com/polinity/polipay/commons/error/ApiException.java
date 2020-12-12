package com.polinity.polipay.commons.error;

public class ApiException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String message;
  private final ErrorCodes errorCode;

  public ApiException(ErrorCodes errorCode) {
    this.errorCode = errorCode;
  }

  public ApiException(Throwable cause, ErrorCodes errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }

  public ApiException(String message, ErrorCodes errorCode) {
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