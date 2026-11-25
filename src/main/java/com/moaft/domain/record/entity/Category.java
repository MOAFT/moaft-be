package com.moaft.domain.record.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category",
        uniqueConstraints = @UniqueConstraint(
                name = "UK_category_type_name",
                columnNames = {"result_type", "name"}
        )
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private ResultType resultType;

    @Column(nullable = false, length = 30)
    private String name;
}
