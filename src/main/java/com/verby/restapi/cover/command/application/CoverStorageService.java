package com.verby.restapi.cover.command.application;

import com.verby.restapi.common.storage.dto.Resource;

public interface CoverStorageService {

    public String getPreSignedUrl(Resource resource);

}
