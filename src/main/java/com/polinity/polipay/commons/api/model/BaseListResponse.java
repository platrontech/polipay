package com.polinity.polipay.commons.api.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class BaseListResponse<T> extends BaseResponse {

    private List<T> results = new ArrayList<>();

    public BaseListResponse<T> append(T t) {
        this.results.add(t);
        return this;
    }

    public BaseListResponse<T> append(Collection<T> t) {
        this.results.addAll(t);
        return this;
    }
}
