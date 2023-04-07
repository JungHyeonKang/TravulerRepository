package com.green4.travuler.community.feed.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass
@EntityListeners(AbstractMethodError.class)
@Getter
public class BaseEntity {

        @CreatedDate
        @Column(updatable = false)
        private LocalDateTime createdDate; //생성날짜 (업데이트불가)

        @LastModifiedDate
        private LocalDateTime updatedDate; // 마지막 수정날짜

}
