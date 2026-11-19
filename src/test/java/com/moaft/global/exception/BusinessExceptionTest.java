package com.moaft.global.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessExceptionTest {

    @Test
    void businessExceptionContainsErrorCode() {
        ErrorCode errorCode = ErrorCode.RECORD_NOT_FOUND;

        BusinessException exception = new BusinessException(errorCode);

        assertThat(exception.getErrorCode()).isEqualTo(errorCode);
        assertThat(exception.getMessage()).isEqualTo(errorCode.getMessage());
    }
}
