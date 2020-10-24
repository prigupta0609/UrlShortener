package com.padlet.controller;

import com.padlet.contract.request.UrlShortenerRequest;
import com.padlet.contract.response.UrlShortenerResponse;
import com.padlet.service.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

// API mapping points
@RestController
public class UrlShortenerController {

    @Autowired
    IUrlService urlService;

    @RequestMapping("/")
    @ResponseBody
    public String isActive() throws Exception {
        return "URL Shortener is active";
    }

    @PostMapping(value = "/url/encode",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UrlShortenerResponse getShortUrl(@RequestBody UrlShortenerRequest request) throws Exception {
        return urlService.encodeUrl(request);
    }

    @GetMapping(value = "/url/decode",
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UrlShortenerResponse getLongUrl(@RequestParam String url) throws Exception {
        return urlService.decodeUrl(url);
    }
}
