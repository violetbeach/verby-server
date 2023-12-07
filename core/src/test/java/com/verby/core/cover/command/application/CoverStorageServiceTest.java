package com.verby.core.cover.command.application;


import com.verby.core.storage.dto.Resource;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CoverStorageServiceTest {

    CoverStoragePathProperties coverStoragePathProperties = new CoverStoragePathProperties(
            "/video", "/highlight", "/image"
    );

    CoverStorageService coverStorageService = new MockCoverStorageService(coverStoragePathProperties);

    @ParameterizedTest
    @MethodSource("paramsForGetPreSignUrl")
    @DisplayName("resource 타입이 존재하면 예외를 발생하지 않는다.")
    void getPreSignUrl(Resource resource) {
        // when & then
        Assertions.assertDoesNotThrow(() -> {
            coverStorageService.getResourcePath(resource);
        });
    }


    static class MockCoverStorageService extends CoverStorageService {
        public MockCoverStorageService(CoverStoragePathProperties storagePathProperties) {
            super(storagePathProperties);
        }

        @Override
        public String getPreSignedUrl(Resource resource, String fileName) {
            getResourcePath(resource);
            return Strings.EMPTY;
        }
    }

    static Stream<Arguments> paramsForGetPreSignUrl() {
        return Stream.of(
                Arguments.of(Resource.FULL_VIDEO),
                Arguments.of(Resource.HIGHLIGHT),
                Arguments.of(Resource.IMAGE)
        );
    }

}