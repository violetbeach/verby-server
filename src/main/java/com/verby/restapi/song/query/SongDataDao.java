package com.verby.restapi.song.query;

import org.springframework.data.repository.Repository;

public interface SongDataDao extends Repository<SongData, String> {

    boolean existsById(Long id);

}
