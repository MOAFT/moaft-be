package com.moaft.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class UserInterestId {
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, columnDefinition = "VARCHAR(30)")
    private Interest interest;
}
