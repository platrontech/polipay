package com.polinity.polipay.commons.api;

import com.polinity.polipay.commons.api.model.ApiResponse;
import com.polinity.polipay.commons.api.model.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthApi extends BaseController {

    @GetMapping
    public ApiResponse<HealthResponse> health() {
        return ok(HealthResponse.builder().status("OK").build());
    }
}
