package com.moaft.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "interest_field",
        uniqueConstraints = @UniqueConstraint(
            name = "UK_interest_field_name", columnNames = "name"
        )
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;
}
