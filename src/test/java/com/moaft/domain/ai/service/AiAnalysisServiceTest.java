
package com.moaft.domain.ai.service;

import com.moaft.domain.ai.dto.AiAnalysisDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Tag("external")
@DisplayName("AI 분석 서비스 통합 테스트")
class AiAnalysisServiceTest {

    @Autowired
    private AiAnalysisService aiAnalysisService;

    @Test
    @DisplayName("제작 과정 분석")
    void analyzeProduction() {
        AiAnalysisDto.Request request = new AiAnalysisDto.Request(
                AiAnalysisDto.RecordType.PRODUCTION,
                "IMAGE",
                "CHARACTER",
                "AI를 활용한 캐릭터 이미지 제작",
                List.of(
                        new AiAnalysisDto.Material(
                                AiAnalysisDto.MaterialType.TEXT,
                                """
                                사용자: 귀여운 흰색 고양이 캐릭터를 만들어줘.
                                AI: 고양이 캐릭터 이미지를 생성했습니다.

                                사용자: 고양이의 눈을 조금 더 크게 만들어줘.
                                AI: 눈 크기를 수정했습니다.

                                사용자: 고양이의 표정을 더 밝게 만들어줘.
                                AI: 표정을 수정했습니다.

                                사용자: 배경을 우주 공간으로 변경해줘.
                                AI: 우주 배경을 적용했습니다.
                                """
                        )
                ),
                "최종 고양이 캐릭터 이미지",
                null
        );

        AiAnalysisDto.ProductionResult result =
                aiAnalysisService.analyzeProduction(request);

        assertNotNull(result);
        assertNotNull(result.title());
        assertNotNull(result.description());
        assertNotNull(result.steps());
        assertFalse(result.steps().isEmpty());

        System.out.println("=== 제작 과정 기본 분석 ===");
        System.out.println("제목: " + result.title());
        System.out.println("설명: " + result.description());

        for (AiAnalysisDto.ProductionStep step : result.steps()) {
            assertNotNull(step.descriptionCandidates());
            assertEquals(3, step.descriptionCandidates().size());

            System.out.println("단계: " + step.name());
            System.out.println("설명 후보: " + step.descriptionCandidates());
            System.out.println("도구: " + step.tools());
            System.out.println("프롬프트: " + step.prompts());
            System.out.println("참고 자료: " + step.references());
            System.out.println("중간 결과물: " + step.intermediateResults());
            System.out.println("----------------------");
        }
    }

    @Test
    @DisplayName("변화 과정 분석")
    void analyzeVariation() {
        AiAnalysisDto.Request request = new AiAnalysisDto.Request(
                AiAnalysisDto.RecordType.VARIATION,
                "IMAGE",
                "CHARACTER",
                "기존 고양이 캐릭터의 디자인 변화",
                List.of(
                        new AiAnalysisDto.Material(
                                AiAnalysisDto.MaterialType.TEXT,
                                """
                                사용자: 기존 흰색 고양이 캐릭터를 검은색으로 변경해줘.
                                AI: 검은색 고양이 캐릭터를 생성했습니다.

                                사용자: 캐릭터의 눈을 노란색으로 변경해줘.
                                AI: 노란색 눈을 적용한 캐릭터 이미지를 생성했습니다.
                                """
                        )
                ),
                null,
                new AiAnalysisDto.ReferenceRecord(
                        "귀여운 고양이 캐릭터 제작",
                        List.of(
                                new AiAnalysisDto.ReferenceStep(
                                        "초기 이미지 생성",
                                        List.of("귀여운 흰색 고양이 캐릭터를 만들어줘."),
                                        List.of("흰색 고양이 캐릭터 이미지")
                                ),
                                new AiAnalysisDto.ReferenceStep(
                                        "배경 변경",
                                        List.of("배경을 우주 공간으로 바꿔줘."),
                                        List.of("우주 배경 고양이 캐릭터 이미지")
                                )
                        )
                )
        );

        AiAnalysisDto.VariationResult result =
                aiAnalysisService.analyzeVariation(request);

        assertNotNull(result);
        assertNotNull(result.title());
        assertNotNull(result.description());
        assertNotNull(result.variations());
        assertFalse(result.variations().isEmpty());

        System.out.println("=== 변화 과정 분석 ===");
        System.out.println("제목: " + result.title());
        System.out.println("설명: " + result.description());

        for (AiAnalysisDto.VariationStep variation : result.variations()) {
            assertNotNull(variation.descriptionCandidates());
            assertEquals(3, variation.descriptionCandidates().size());

            System.out.println("변화: " + variation.name());
            System.out.println("설명 후보: " + variation.descriptionCandidates());
            System.out.println("도구: " + variation.tools());
            System.out.println("프롬프트: " + variation.prompt());
            System.out.println("결과물: " + variation.result());
            System.out.println("연결 관계: " + variation.connections());
            System.out.println("----------------------");
        }
    }
}
