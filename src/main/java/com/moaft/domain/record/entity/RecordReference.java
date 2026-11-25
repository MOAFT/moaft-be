package com.moaft.domain.record.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "record_reference")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_record_id", nullable = false)
    private Record targetRecord;                // 참조를 사용하는 변화 과정 기록

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_record_id")
    private Record sourceRecord;                // 참조 대상 제작 과정 기록 (원본 삭제 시 null)

    @Column(columnDefinition = "JSON")
    private String snapshotData;                // 참조 시점의 데이터 복사본

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime referencedAt;
}