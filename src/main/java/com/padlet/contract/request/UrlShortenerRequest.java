package com.padlet.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

// Contains parameters of request
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlShortenerRequest {

    @JsonProperty("url")
    @NotNull
    private String url;

    public String getUrl() {
        return url;
    }
}
