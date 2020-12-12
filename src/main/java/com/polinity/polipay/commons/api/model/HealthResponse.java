package com.polinity.polipay.commons.api.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HealthResponse extends BaseResponse {
    String status;
}
