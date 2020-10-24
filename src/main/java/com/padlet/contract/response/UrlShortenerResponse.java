package com.padlet.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// Contains parameters of response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlShortenerResponse implements Serializable {

    @JsonProperty("shortUrl")
    private final String shortUrl;

    @JsonProperty("longUrl")
    private final String longUrl;

    @JsonProperty("error")
    private final Error error;

    public UrlShortenerResponse(String shortUrl, String longUrl, Error error) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.error = error;
    }

    public static class UrlShortenerResponseBuilder {
        private String shortUrl;
        private String longUrl;
        private Error error;

        public UrlShortenerResponseBuilder() {
        }

        public UrlShortenerResponseBuilder setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
            return this;
        }

        public UrlShortenerResponseBuilder setLongUrl(String longUrl) {
            this.longUrl = longUrl;
            return this;
        }

        public UrlShortenerResponseBuilder setError(Error error) {
            this.error = error;
            return this;
        }

        public UrlShortenerResponse build() {
            return new UrlShortenerResponse(this.shortUrl, this.longUrl, this.error);
        }
    }
}
