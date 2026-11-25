package com.moaft.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "policy_document",
        uniqueConstraints = @UniqueConstraint(
                name = "UK_policy_document_type_version",
                columnNames = {"document_type", "version"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30, columnDefinition = "VARCHAR(30)")
    private PolicyDocumentType documentType;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 20)
    private String version;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isRequired = true;
}
