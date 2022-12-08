package com.verby.restapi.song.command.application;

import com.verby.restapi.common.storage.dto.Resource;

public interface SongStorageService {

    public String getPreSignedUrl(Resource resource);

}
