package com.padlet.service;

import com.padlet.contract.request.UrlShortenerRequest;
import com.padlet.contract.response.UrlShortenerResponse;

// Service layer to process the requests
public class UrlService implements IUrlService{

    @Override
    public UrlShortenerResponse encodeUrl(UrlShortenerRequest request) {
        return null;
    }

    @Override
    public UrlShortenerResponse decodeUrl(String shortUrl) {
        return null;
    }
}
