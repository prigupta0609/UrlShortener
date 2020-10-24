package com.padlet;

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
    public void testGetLongUrl() throws Exception {
        UrlShortenerResponse response = insertNewShortUrl(getShortUrlRequestPath);
        UrlShortenerResponse finalResponse = fetchShortUrlFromLongUrl(response.getShortUrl());
        Assert.assertNotNull(finalResponse);
    }

    private UrlShortenerResponse insertNewShortUrl(String filePath) throws Exception{
        JSONObject request = (JSONObject) parser.parse(new FileReader(filePath));
        HttpEntity<String> httpEntity = getHttpRequestJSON(request);
        return this.restTemplate
                .postForObject("http://localhost:"+port+"/url/encode",
                        httpEntity, UrlShortenerResponse.class);
    }

    private UrlShortenerResponse fetchShortUrlFromLongUrl(String url) throws Exception {
        //JSONObject request = (JSONObject) parser.parse(url);
        //HttpEntity<String> httpEntity = getHttpRequestJSON(request);
        return this.restTemplate
                .getForObject("http://localhost:"+port+"/url/decode?url=" + url, UrlShortenerResponse.class);
    }

    private HttpEntity<String> getHttpRequestJSON(JSONObject request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<String>(request.toJSONString(), headers);
    }
}
