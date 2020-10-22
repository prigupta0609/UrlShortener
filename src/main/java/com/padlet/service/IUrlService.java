package com.padlet.service;

import com.padlet.contract.request.UrlShortenerRequest;
import com.padlet.contract.response.UrlShortenerResponse;

// Contract of the services exposed by UrlService
public interface IUrlService {
    public UrlShortenerResponse encodeUrl(UrlShortenerRequest request);
    public UrlShortenerResponse decodeUrl(String shortUrl);
}
