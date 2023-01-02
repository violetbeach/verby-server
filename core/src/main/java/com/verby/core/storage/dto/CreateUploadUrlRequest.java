package com.verby.core.storage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateUploadUrlRequest {

    @NotBlank
    private String domainType;
    @NotBlank
    private String resourceType;

    public Domain getDomainType() {
        return Domain.valueOf(domainType);
    }

    public Resource getResourceType() {
        return Resource.valueOf(resourceType);
    }
}
