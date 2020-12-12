package com.polinity.polipay.context.payment.api;

import com.polinity.polipay.commons.api.BaseController;
import com.polinity.polipay.commons.api.model.ApiResponse;
import com.polinity.polipay.commons.api.model.DoneResponse;
import com.polinity.polipay.context.payment.PaymentService;
import com.polinity.polipay.context.payment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentApi extends BaseController {

    private final PaymentService paymentService;

    @PostMapping("/auth/saved-card")
    public ApiResponse<DoneResponse> authWithSavedCard(@RequestBody PaymentRequest paymentRequest) {
        return ok(paymentService.authWithSavedCard(paymentRequest));
    }
}
