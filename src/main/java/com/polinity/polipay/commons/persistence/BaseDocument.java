package com.polinity.polipay.commons.persistence;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
public class BaseDocument implements Serializable {

    @Id
    protected ObjectId id;

    @Builder.Default
    protected LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    protected LocalDateTime updatedAt = LocalDateTime.now();
}
