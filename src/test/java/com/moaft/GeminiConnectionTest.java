package com.moaft;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.*;

@Tag("external")
@SpringBootTest
@ActiveProfiles("local")
class GeminiConnectionTest {

    @Autowired
    private ChatClient.Builder chatClientBuilder;

    @Test
    void geminiConnectionTest() {
        ChatClient chatClient = chatClientBuilder.build();

        System.out.println("Gemini API 호출 시작");

        String response = chatClient.prompt()
                .user("안녕하세요. 한 문장으로 자기소개해 주세요.")
                .call()
                .content();

        System.out.println("Gemini API 호출 완료");
        System.out.println("Gemini 응답: " + response);

        assertNotNull(response);
        assertFalse(response.isBlank());
    }
}
