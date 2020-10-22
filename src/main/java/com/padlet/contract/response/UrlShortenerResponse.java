package com.padlet.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// Contains parameters of response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlShortenerResponse {

    @JsonProperty("url")
    private String url;

    @JsonProperty("error")
    private Error error;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
