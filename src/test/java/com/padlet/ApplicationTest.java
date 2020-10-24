package com.padlet;

import com.padlet.common.Error;
import com.padlet.contract.response.UrlShortenerResponse;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileReader;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    private static final String getShortUrlRequestPath = "src/test/resources/GetShortUrlRequest.json";
    private static final String getShortUrlRequest_InvalidUrlFormat_Path = "src/test/resources/GetShortUrlRequest_InvalidUrlFormat.json";
    private static final String emptyUrlRequest = "src/test/resources/EmptyUrlRequest.json";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static JSONParser parser = null;

    @BeforeClass
    public static void setUp() {
        parser = new JSONParser();
    }

    @Test
    public void testService() {
        Assert.assertEquals("URL Shortener is active",this.restTemplate
                .getForObject("http://localhost:"+port+"/", String.class));
    }

    @Test
    public void testGetShortUrl() throws Exception {
        UrlShortenerResponse response = insertNewShortUrl(getShortUrlRequestPath);
        Assert.assertNotNull(response);
    }

    @Test
    public void testEmptyUrlRequest() throws Exception {
        UrlShortenerResponse response = insertNewShortUrl(emptyUrlRequest);
        Assert.assertNotNull(response);
        Assert.assertNull(response.getShortUrl());
        Assert.assertNotNull(response.getError());
        Assert.assertEquals(Error.INVALID_URL_FORMAT.getErrorCode(), response.getError().getCode());
    }

    @Test
    public void testGetShortUrl_InvalidUrlFormat() throws Exception {
        UrlShortenerResponse response = insertNewShortUrl(getShortUrlRequest_InvalidUrlFormat_Path);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getError());
        Assert.assertEquals(Error.INVALID_URL_FORMAT.getErrorCode(), response.getError().getCode());
    }

    @Test
    public void testGetLongUrl() throws Exception {
        UrlShortenerResponse response = insertNewShortUrl(getShortUrlRequestPath);
        UrlShortenerResponse finalResponse = fetchShortUrlFromLongUrl(response.getShortUrl());
        Assert.assertNotNull(finalResponse);
    }

    @Test
    public void testWrongLongUrl() throws Exception {
        UrlShortenerResponse response = fetchShortUrlFromLongUrl("http://www.sample.com/tr451e");
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getError());
        Assert.assertEquals(Error.URL_NOT_FOUND.getErrorCode(), response.getError().getCode());
    }

    private UrlShortenerResponse insertNewShortUrl(String filePath) throws Exception{
        JSONObject request = (JSONObject) parser.parse(new FileReader(filePath));
        HttpEntity<String> httpEntity = getHttpRequestJSON(request);
        return this.restTemplate
                .postForObject("http://localhost:"+port+"/url/encode",
                        httpEntity, UrlShortenerResponse.class);
    }

    private UrlShortenerResponse fetchShortUrlFromLongUrl(String url) throws Exception {
        return this.restTemplate
                .getForObject("http://localhost:"+port+"/url/decode?url=" + url, UrlShortenerResponse.class);
    }

    private HttpEntity<String> getHttpRequestJSON(JSONObject request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<String>(request.toJSONString(), headers);
    }
}
