package com.gosterici.adesso.employeeservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PACKAGE)
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private UUID id;
    @Column(name = "insert_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime insertTime;
    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateTime;

    @PrePersist
    private void prePersist() {
        this.insertTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
