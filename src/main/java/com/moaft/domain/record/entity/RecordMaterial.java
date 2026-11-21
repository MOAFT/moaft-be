package com.moaft.domain.record.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "record_material")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_asset_id")
    private MediaAsset mediaAsset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_step_id")
    private ProductionStep productionStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_item_id")
    private VariationItem variationItem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private MaterialType materialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30, columnDefinition = "VARCHAR(30)")
    private MaterialRole materialRole;

    @Column(columnDefinition = "TEXT")
    private String textContent;

    @Column(length = 2000)
    private String externalUrl;

    @Column(nullable = false)
    private Integer displayOrder = 0;
}