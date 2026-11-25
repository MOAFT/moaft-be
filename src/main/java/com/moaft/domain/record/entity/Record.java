package com.moaft.domain.record.entity;

import com.moaft.domain.user.entity.User;
import com.moaft.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "record")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private RecordType recordType;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, columnDefinition = "VARCHAR(20)")
    private ResultType resultType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer estimatedDuration;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPublic = false;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isCopyable = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private RecordStatus status = RecordStatus.DRAFT;

    @Column(nullable = false, length = 36, unique = true, columnDefinition = "CHAR(36)")
    private String shareKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copied_from_record_id")
    private Record copiedFrom;

    @Column(nullable = false)
    private Integer viewCount = 0;
}