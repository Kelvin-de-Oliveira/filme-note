package com.kelvin.filme_note.shared.audit;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

}
