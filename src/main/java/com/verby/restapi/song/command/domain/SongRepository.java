package com.verby.restapi.song.command.domain;

import org.springframework.data.repository.Repository;

public interface SongRepository extends Repository<Song, Long> {

    Song save(Song song);

}
