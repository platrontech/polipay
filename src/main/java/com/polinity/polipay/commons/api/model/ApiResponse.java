package com.polinity.polipay.commons.api.model;

public class ApiResponse<T extends BaseResponse> {

  private int code;
  private String message;
  private T data;

  public ApiResponse(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getResult() {
    return data;
  }

  public void setResult(T result) {
    this.data = data;
  }
}
