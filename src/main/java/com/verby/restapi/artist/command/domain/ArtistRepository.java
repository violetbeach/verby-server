package com.verby.restapi.artist.command.domain;

import org.springframework.data.repository.Repository;

public interface ArtistRepository extends Repository<Artist, Long> {
    Artist save(Artist account);

}
