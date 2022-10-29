package com.verby.restapi.cover.command.application;

import com.verby.restapi.common.storage.StaticStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("CoverStorageService의")
class CoverStorageServiceTest {
    CoverStorageService coverStorageService;
    @Mock
    StaticStorage staticStorage;
    CoverStoragePathProperties storagePathProperties;

    @BeforeEach
    void setup() {
        storagePathProperties = new CoverStoragePathProperties("video", "highlight", "image");
        coverStorageService = new CoverStorageService(staticStorage, storagePathProperties);
    }


    @Nested
    @DisplayName("uploads 메서드는")
    class uploads {

        List<String> mockResult;

        MockMultipartFile video = new MockMultipartFile("video", "cover.mp4", "video/mp4",  "Video Binary Data".getBytes());
        MockMultipartFile highlight = new MockMultipartFile("highlight", "highlight.mp4", "video/mp4", "Video Binary Data".getBytes());
        MockMultipartFile thumbnail = new MockMultipartFile("image", "thumbnail.png", "image/png", "Image Binary Data".getBytes());

        @BeforeEach
        void setup() {
            mockResult = new ArrayList<>(List.of(new String[]{"/static/resource/1", "/static/resource/2", "/static/resource/3"}));

            given(staticStorage.upload(any(MultipartFile.class), anyString()))
                    .willReturn(mockResult.get(0))
                    .willReturn(mockResult.get(1))
                    .willReturn(mockResult.get(2));
        }

        @Test
        @DisplayName("UploadCoverResourceRequest로 해당 자원들을 스토리지에 업로드한다.")
        void success() {
            // given
            UploadCoverResourceRequest request = new UploadCoverResourceRequest(video, highlight, thumbnail);

            // when
            CoverStoragePathProperties result = coverStorageService.uploads(request);

            // then
            assertThat(result.getVideo()).isEqualTo(mockResult.get(0));
            assertThat(result.getHighlight()).isEqualTo(mockResult.get(1));
            assertThat(result.getImage()).isEqualTo(mockResult.get(2));
        }

    }

}