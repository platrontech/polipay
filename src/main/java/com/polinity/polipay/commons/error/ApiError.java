package com.polinity.polipay.commons.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ApiError {

  HttpStatus status;
  int code;
  @Builder.Default
  LocalDateTime timestamp = LocalDateTime.now();
  String message;
  String debugMessage;
  List<ApiSubError> subErrors;
}