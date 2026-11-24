
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
사용자: 귀여운 흰색 고양이 캐릭터를 만들어줘. 전체적으로 머리가 크고 몸이 작은 2.5등신 비율의 3D 애니메이션 스타일로 만들어줘. 털은 순백색이고 부드럽고 폭신폭신한 질감이 느껴졌으면 좋겠어. 눈동자는 맑고 선명한 하늘색이며, 동그란 눈 안에는 작은 흰색 하이라이트가 들어가 있어야 해. 양쪽 귀는 삼각형 모양이고 귀 끝부분만 검정색으로 표현해줘. 꼬리는 길고 부드럽게 휘어진 형태이며 꼬리 끝 약 20%만 검정색이야. 코는 작고 분홍색이고, 입은 작은 'ω' 모양으로 만들어줘. 양쪽 볼에는 연한 분홍색 홍조를 추가해줘. 고양이는 정면을 바라보며 두 발로 서 있고, 앞발은 몸 앞에 자연스럽게 모으고 있어. 배경은 따뜻한 크림색 단색으로 하고 부드러운 스튜디오 조명을 적용해줘. 캐릭터의 전신이 화면 중앙에 보이도록 정사각형 비율로 생성해줘.

ChatGPT: 요청하신 특징을 반영하여 귀여운 흰색 고양이 캐릭터 이미지를 생성했습니다. 순백색의 폭신한 털과 하늘색 눈동자, 검정색 귀 끝과 꼬리 끝을 적용했으며, 2.5등신 비율의 3D 애니메이션 스타일로 표현했습니다. 배경은 따뜻한 크림색으로 설정했습니다.

사용자: 전체적인 디자인은 마음에 드는데 눈이 조금 작은 것 같아. 고양이의 눈을 지금보다 약 30% 정도 더 크게 만들어줘. 눈의 가로와 세로 크기를 균형 있게 확대해서 더욱 귀엽고 사랑스러운 인상이 느껴지도록 해줘. 눈동자의 하늘색은 그대로 유지하고, 눈 안에 들어간 흰색 하이라이트도 눈 크기에 맞게 자연스럽게 조정해줘. 눈 사이의 간격도 얼굴 비율에 맞게 조절해주면 좋겠어. 다만 눈을 제외한 다른 요소들은 변경하지 않았으면 해. 특히 흰색 털, 검정색 귀 끝과 꼬리 끝, 분홍색 코, 입 모양, 체형, 자세, 크림색 배경은 기존 이미지와 동일하게 유지해줘.

ChatGPT: 고양이의 눈 크기를 기존보다 약 30% 확대하여 수정했습니다. 하늘색 눈동자와 하이라이트를 유지하면서 얼굴 비율에 맞게 눈의 크기와 간격을 조정했습니다. 눈 이외의 캐릭터 외형과 배경은 기존 디자인을 그대로 유지했습니다.

사용자: 눈 크기는 이제 마음에 들어. 그런데 고양이의 표정이 조금 무표정해 보여서 더 밝고 행복한 느낌으로 바꾸고 싶어. 입을 살짝 벌린 환한 미소로 변경하고, 작은 분홍색 혀가 살짝 보이도록 해줘. 입꼬리는 양쪽으로 올라가서 정말 기분이 좋아 보였으면 좋겠어. 눈은 방금 수정한 커다란 하늘색 눈을 그대로 유지하되, 눈매에서 기쁨과 설렘이 느껴지도록 표현해줘. 양쪽 볼의 분홍색 홍조도 기존보다 조금 더 선명하게 만들어줘. 마치 고양이가 좋아하는 사람을 만나서 행복해하는 듯한 표정이면 좋겠어. 단, 표정과 관련된 부분 외에는 수정하지 말아줘. 귀와 꼬리의 색상, 체형, 자세, 배경은 그대로 유지해줘.

ChatGPT: 고양이의 표정을 더욱 밝고 행복한 모습으로 수정했습니다. 입꼬리가 올라간 환한 미소와 살짝 보이는 분홍색 혀를 추가하고, 볼의 홍조를 강조했습니다. 이전에 확대했던 하늘색 눈과 캐릭터의 나머지 디자인은 그대로 유지했습니다.

사용자: 이제 고양이 캐릭터 자체는 완성된 것 같아. 마지막으로 배경을 완전히 바꿔보고 싶어. 기존의 크림색 스튜디오 배경 대신 신비롭고 아름다운 우주 공간으로 변경해줘. 배경은 깊은 남색과 보라색이 자연스럽게 어우러진 광활한 우주였으면 좋겠어. 주변에는 크고 작은 별들이 반짝이고, 멀리에는 푸른빛과 보랏빛 성운이 펼쳐져 있도록 해줘. 고양이 뒤쪽에는 은은하게 빛나는 커다란 행성을 하나 배치하고, 주변에는 작은 우주 먼지와 별빛이 흩어져 있으면 좋겠어. 전체적으로 환상적이면서도 귀여운 분위기를 유지하고, 우주에서 나오는 은은한 빛이 고양이의 흰색 털에 자연스럽게 반사되도록 표현해줘. 하지만 고양이 자체는 절대 변경하지 말아줘. 커다란 하늘색 눈, 검정색 귀 끝과 꼬리 끝, 행복한 미소, 분홍색 코와 볼, 통통한 2.5등신 체형, 정면을 바라보는 자세까지 모두 그대로 유지해야 해. 기존 고양이가 우주 공간으로 이동한 것처럼 배경만 자연스럽게 변경해줘.

ChatGPT: 기존 고양이 캐릭터의 외형과 표정을 유지하면서 배경을 신비로운 우주 공간으로 변경했습니다. 남색과 보라색이 어우러진 우주에 반짝이는 별, 푸른빛과 보랏빛 성운, 은은하게 빛나는 행성을 추가했습니다. 이전 단계에서 수정한 커다란 하늘색 눈과 행복한 표정도 그대로 유지했습니다.
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
        System.out.println("======================");

        for (AiAnalysisDto.ProductionStep step : result.steps()) {
            assertNotNull(step.candidates());
            assertEquals(3, step.candidates().size());

            System.out.println("단계: " + step.name());
            System.out.println("도구: " + step.tools());
            for (AiAnalysisDto.Candidate candidate : step.candidates()) {
                System.out.println("=== 후보 ===");
                for (AiAnalysisDto.ProductionDescription description : candidate.productionDescriptions()) {
                    System.out.println("설명: " + description.description());
                    System.out.println("프롬프트: " + description.prompts());
                }
            }
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
        System.out.println("======================");

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
