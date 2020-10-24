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
            String shortUrl = Constants.INITIAL_URL + "a23de1";
            URL url = new URL(shortUrl, request.getUrl());
            urlDao.addUrlMapping("a23de1", url);
            response = getResponse(shortUrl, request, null);
        } catch (ValidationException e) {
            Error error = new Error(e.getMessage(), e.getErrorCode());
            response = getResponse(null, request, error);
        }
        return response;
    }

    @Override
    public UrlShortenerResponse decodeUrl(String shortUrl) {
        return null;
    }

    private UrlShortenerResponse getResponse(String shortUrl, UrlShortenerRequest request,
                                             Error error) {
        UrlShortenerResponse.UrlShortenerResponseBuilder builder
                = new UrlShortenerResponse.UrlShortenerResponseBuilder();
        return builder.setShortUrl(shortUrl)
                .setLongUrl(request.getUrl())
                .setError(error)
                .build();
    }
}
