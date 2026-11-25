package com.moaft.domain.record.entity;

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
@Table(
        name = "saved_record",
        uniqueConstraints = @UniqueConstraint(
                name = "UK_saved_record_user_record",
                columnNames = {"user_id", "record_id"}
        )
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;                      // 원본 삭제 시 null → 안내 썸네일 표시

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime savedAt;
}