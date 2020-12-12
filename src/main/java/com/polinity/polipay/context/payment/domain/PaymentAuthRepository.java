package com.polinity.polipay.context.payment.domain;

import org.springframework.data.repository.CrudRepository;

public interface PaymentAuthRepository extends CrudRepository<PaymentAuthDocument, String> {
}
