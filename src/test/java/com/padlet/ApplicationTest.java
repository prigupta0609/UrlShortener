package com.padlet;

import com.padlet.contract.request.UrlShortenerRequest;
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
        JSONObject request = (JSONObject) parser.parse(new FileReader(getShortUrlRequestPath));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(request.toJSONString(), headers);
        UrlShortenerResponse response = this.restTemplate
                .postForObject("http://localhost:"+port+"/url/encode",
                        httpEntity, UrlShortenerResponse.class);
        Assert.assertNotNull(response);
    }
}
