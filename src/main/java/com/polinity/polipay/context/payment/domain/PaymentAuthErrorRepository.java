package com.polinity.polipay.context.payment.domain;

import org.springframework.data.repository.CrudRepository;

public interface PaymentAuthErrorRepository extends CrudRepository<PaymentAuthErrorDocument, String> {
}
