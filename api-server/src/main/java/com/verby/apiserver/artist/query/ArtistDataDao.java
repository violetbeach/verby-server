package com.verby.apiserver.artist.query;

import org.springframework.data.repository.Repository;

public interface ArtistDataDao extends Repository<ArtistData, String> {

    boolean existsById(Long id);

}
