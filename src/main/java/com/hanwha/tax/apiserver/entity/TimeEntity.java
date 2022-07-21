package com.hanwha.tax.apiserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {
    @JsonIgnore
    @CreationTimestamp
    @Column(name="create_dt", updatable = false, nullable = false, columnDefinition = "datetime not null comment '생성일'")
    private LocalDateTime createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name="update_dt", columnDefinition = "datetime null comment '수정일'")
    private LocalDateTime updatedDate;
}
