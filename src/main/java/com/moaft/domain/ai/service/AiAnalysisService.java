package com.moaft.domain.ai.service;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
import com.moaft.domain.ai.dto.AiAnalysisDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class AiAnalysisService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;
    private final PromptTemplate productionPrompt;
    private final PromptTemplate variationPrompt;

    public AiAnalysisService(
            ChatClient.Builder chatClientBuilder,
            ObjectMapper objectMapper
    ) {
        this.chatClient = chatClientBuilder.build();
        this.objectMapper = objectMapper;

        this.productionPrompt = new PromptTemplate(
                new ClassPathResource("prompts/production-analysis.st")
        );

        this.variationPrompt = new PromptTemplate(
                new ClassPathResource("prompts/variation-analysis.st")
        );
    }

    public AiAnalysisDto.ProductionResult analyzeProduction(
            AiAnalysisDto.Request request
    ) {
        validateRequest(request, AiAnalysisDto.RecordType.PRODUCTION);

        String prompt = productionPrompt.render(
                Map.of("analysis_input", toJson(request))
        );

        AiAnalysisDto.ProductionResult result = chatClient.prompt()
                .user(prompt)
                .call()
                .entity(AiAnalysisDto.ProductionResult.class);

        return Objects.requireNonNull(
                result, "제작 과정 AI 분석 결과가 비어 있습니다."
        );
    }

    public AiAnalysisDto.VariationResult analyzeVariation(
            AiAnalysisDto.Request request
    ) {
        validateRequest(request, AiAnalysisDto.RecordType.VARIATION);

        String prompt = variationPrompt.render(
                Map.of("analysis_input", toJson(request))
        );

        AiAnalysisDto.VariationResult result = chatClient.prompt()
                .user(prompt)
                .call()
                .entity(AiAnalysisDto.VariationResult.class);

        return Objects.requireNonNull(
                result, "변화 과정 AI 분석 결과가 비어 있습니다."
        );
    }

    private void validateRequest(
            AiAnalysisDto.Request request,
            AiAnalysisDto.RecordType expectedType
    ) {
        if (request == null || request.recordType() != expectedType) {
            throw new IllegalArgumentException(
                    "올바르지 않은 AI 분석 요청 유형입니다."
            );
        }
    }

    private String toJson(AiAnalysisDto.Request request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JacksonException e) {
            throw new IllegalArgumentException(
                    "AI 분석 입력 데이터를 JSON으로 변환하지 못했습니다.", e
            );
        }
    }
}
