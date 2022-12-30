package com.verby.apiserver.cover.command.application;


import com.verby.apiserver.common.storage.dto.Resource;
import org.apache.logging.log4j.util.Strings;
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
    void getPreSignUrl(Resource resource) {
        // when
        coverStorageService.getResourcePath(resource);

        // then
        // - Not Throw Exception
    }


    static class MockCoverStorageService extends CoverStorageService {
        public MockCoverStorageService(CoverStoragePathProperties storagePathProperties) {
            super(storagePathProperties);
        }

        @Override
        public String getPreSignedUrl(Resource resource) {
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