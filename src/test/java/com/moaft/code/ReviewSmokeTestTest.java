package com.moaft.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewSmokeTestTest {

    @Test
    @DisplayName("만 18세는 가입할 수 있다 (경계값)")
    void canRegister_age18() {
        assertThat(ReviewSmokeTest.canRegister(18)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {19, 30, 100})
    @DisplayName("만 18세 초과는 가입할 수 있다")
    void canRegister_over18(int age) {
        assertThat(ReviewSmokeTest.canRegister(age)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 17})
    @DisplayName("만 18세 미만은 가입할 수 없다")
    void cannotRegister_under18(int age) {
        assertThat(ReviewSmokeTest.canRegister(age)).isFalse();
    }
}
