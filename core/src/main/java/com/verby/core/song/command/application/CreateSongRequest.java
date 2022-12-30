package com.verby.core.song.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateSongRequest {

    @JsonIgnore
    private Long artistId;
    @NotBlank
    private String name;
    private String image;

    public CreateSongRequest(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

}
