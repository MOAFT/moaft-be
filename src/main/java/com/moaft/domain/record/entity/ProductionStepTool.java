package com.moaft.domain.record.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "production_step_tool")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductionStepTool {

    @EmbeddedId
    private ProductionStepToolId id;

    @MapsId("productionStepId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_step_id")
    private ProductionStep productionStep;

    @MapsId("toolId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_id")
    private Tool tool;
}