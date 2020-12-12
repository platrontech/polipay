package com.polinity.polipay.commons.api;

import com.polinity.polipay.commons.api.model.ApiResponse;
import com.polinity.polipay.commons.api.model.BaseResponse;
import org.springframework.http.HttpStatus;

public class BaseController {

    protected <T extends BaseResponse> ApiResponse<T> ok(T response) {
        return new ApiResponse<>(HttpStatus.OK.value(), "OK", response);
    }
}
