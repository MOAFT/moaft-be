package com.moaft.domain.record.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tool",
        uniqueConstraints = @UniqueConstraint(
                name = "UK_tool_name",
                columnNames = "name"
        )
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isPreset = false;

}
