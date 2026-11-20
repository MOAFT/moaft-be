package com.moaft.domain.ai.dto;

import java.util.List;

public class AiAnalysisDto {

    public enum RecordType {
        PRODUCTION,
        VARIATION
    }

    public enum MaterialType {
        TEXT,
        IMAGE,
        LINK,
        FILE
    }

    public record Request(
            RecordType recordType,
            String resultType,
            String category,
            String workSummary,
            List<Material> materials,
            String finalResult,
            ReferenceRecord referenceRecord
    ) {
    }

    public record Material(
            MaterialType type,
            String content
    ) {
    }

    public record ReferenceRecord(
            String title,
            List<ReferenceStep> steps
    ) {
    }

    public record ReferenceStep(
            String name,
            List<String> prompts,
            List<String> results
    ) {
    }

    public record ProductionResult(
            String title,
            String description,
            List<ProductionCandidate> candidates
    ) {
    }

    public record ProductionCandidate(
            String name,
            String description,
            List<String> tools,
            List<String> prompts,
            List<String> references,
            List<String> intermediateResults
    ) {
    }

    public record VariationResult(
            String title,
            String description,
            List<VariationCandidate> candidates
    ) {
    }

    public record VariationCandidate(
            String name,
            String description,
            List<String> tools,
            String prompt,
            String result,
            List<String> connections
    ) {
    }
}
