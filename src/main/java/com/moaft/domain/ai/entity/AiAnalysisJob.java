package com.moaft.domain.ai.entity;

import com.moaft.domain.record.entity.Record;
import com.moaft.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ai_analysis_job")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiAnalysisJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;                      // 분석 대상 초안 (선택)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private AnalysisType analysisType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private AnalysisJobStatus status = AnalysisJobStatus.PENDING;

    @Column(length = 500)
    private String errorMessage;                // 실패했을 때만

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime requestedAt;

    private LocalDateTime completedAt;          // 완료됐을 때만, 코드에서 직접 넣음

    @Column(nullable = true, columnDefinition = "JSON")
    private String resultData;                  // AI 분석 후보 전체 (완료 전·실패 시 null)
}