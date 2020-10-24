package com.padlet.service;

import com.padlet.common.Constants;
import com.padlet.contract.request.UrlShortenerRequest;
import com.padlet.contract.response.Error;
import com.padlet.contract.response.UrlShortenerResponse;
import com.padlet.dao.IUrlDao;
import com.padlet.model.URL;
import com.padlet.validator.IValidator;
import com.padlet.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// Service layer to process the requests
@Service
public class UrlService implements IUrlService {

    @Autowired
    @Qualifier("UrlDao")
    private IUrlDao urlDao;

    @Autowired
    @Qualifier("UrlValidator")
    private IValidator urlValidator;

    private static int counter = 1;

    @Override
    public UrlShortenerResponse encodeUrl(UrlShortenerRequest request) {
        UrlShortenerResponse response = null;
        try {
            urlValidator.validate(request.getUrl());
            String shortUrl = getShortUrl(request);
            response = createResponse(shortUrl, request.getUrl(), null);
        } catch (ValidationException e) {
            Error error = new Error(e.getMessage(), e.getErrorCode());
            response = createResponse(null, request.getUrl(), error);
        }
        return response;
    }

    @Override
    public UrlShortenerResponse decodeUrl(String shortUrl) {
        UrlShortenerResponse response = null;
        URL url = getLongUrl(shortUrl);
        if (url == null) {
            Error error = new Error(com.padlet.common.Error.URL_NOT_FOUND.getMessage(),
                    com.padlet.common.Error.URL_NOT_FOUND.getErrorCode());
            response = createResponse(shortUrl, null, error);
        } else {
            response = createResponse(shortUrl, url.getLongUrl(), null);
        }
        return response;
    }

    private URL getLongUrl(String shortUrl) {
        String hashCode = shortUrl.substring(shortUrl.length()-6);
        return urlDao.getUrlFromHashcode(hashCode);
    }

    private String getShortUrl(UrlShortenerRequest request) {
        String hashCode = UrlUtil.getBase62(counter);
        String shortUrl = Constants.INITIAL_URL + hashCode;
        URL url = new URL(shortUrl, request.getUrl());
        urlDao.addUrlMapping(hashCode, url);
        return shortUrl;
    }

    private UrlShortenerResponse createResponse(String shortUrl, String longUrl,
                                                Error error) {
        UrlShortenerResponse.UrlShortenerResponseBuilder builder
                = new UrlShortenerResponse.UrlShortenerResponseBuilder();
        return builder.setShortUrl(shortUrl)
                .setLongUrl(longUrl)
                .setError(error)
                .build();
    }
}
