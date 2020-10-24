package com.padlet.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

// Contains parameters of request
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlShortenerRequest implements Serializable {

    @JsonProperty("url")
    @NotNull
    private String url;

    public UrlShortenerRequest(){}

    public String getUrl() {
        return url;
    }
}
