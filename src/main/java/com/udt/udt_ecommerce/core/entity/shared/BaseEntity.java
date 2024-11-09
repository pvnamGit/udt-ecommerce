package com.udt.udt_ecommerce.core.entity.shared;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@Lazy(value = false)
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Long createdAt = Instant.now().toEpochMilli();

    @Column(name = "updated_at")
    private Long updatedAt = Instant.now().toEpochMilli();

    @Column(name = "is_active")
    private Boolean isActive = true;
}