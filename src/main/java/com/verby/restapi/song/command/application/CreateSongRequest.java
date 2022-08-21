package com.verby.restapi.song.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateSongRequest {

    @JsonIgnore
    private Long artistId;
    @NotBlank
    private String name;

    public CreateSongRequest(String name) {
        this.name = name;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

}
