package com.polinity.polipay.commons.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ApiException.class})
  protected ResponseEntity<ApiError> handleApiException(ApiException ex) {
    ApiError.ApiErrorBuilder errorBuilder = ApiError.builder();
    errorBuilder.status(HttpStatus.OK);
    errorBuilder.code(ex.getErrorCode().getCode());

    if (ex.getErrorCode().isLogError()) {
      logger.error(ex.getErrorCode(), ex.getCause());
    }
    errorBuilder
        .message(ex.getErrorCode().getError())
        .debugMessage(ex.getErrorCode().getError());

    return handleError(HttpStatus.OK, errorBuilder.build());
  }

  @ExceptionHandler({AccessDeniedException.class})
  protected ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
    logger.error(ex.getMessage(), ex);
    ApiError apiError = ApiError.builder()
        .code(HttpStatus.FORBIDDEN.value())
        .status(HttpStatus.FORBIDDEN)
        .debugMessage("unauthorized api access")
        .message(ErrorCodes.UNAUTHORIZED.getError())
        .build();

    return handleError(HttpStatus.FORBIDDEN, apiError);
  }

  @ExceptionHandler({Exception.class})
  protected ResponseEntity<ApiError> handleUnhandledException(Exception ex) {
    logger.error(ex.getMessage(), ex);
    ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    return handleError(HttpStatus.INTERNAL_SERVER_ERROR, apiError);
  }

  private ResponseEntity<ApiError> handleError(HttpStatus status, ApiError apiError) {
    return ResponseEntity.status(status).body(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<ApiSubError> validationErrors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> new ApiValidationError(fieldError.getField(), String.valueOf(fieldError.getRejectedValue()),
            ValidationErrorMessages.getMessageByField(fieldError.getField())))
        .collect(Collectors.toList());

    ApiError apiError = ApiError.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .status(HttpStatus.BAD_REQUEST)
        .message(ValidationErrorMessages.INVALID_VALUES_ERROR.getMessage())
        .debugMessage(ex.getMessage())
        .subErrors(validationErrors)
        .build();

    return ResponseEntity.status(status).body(apiError);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return super.handleHttpMessageNotWritable(ex, headers, status, request);
  }
}
