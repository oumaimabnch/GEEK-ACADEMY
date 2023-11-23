package com.gdev.geekacademybackend.models.Auditing;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAuditor {

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "updated_at")
    @LastModifiedDate
    private long modifiedDate;

}
