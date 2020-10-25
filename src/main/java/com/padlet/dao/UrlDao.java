package com.padlet.dao;

import com.padlet.model.URL;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// In-memory DAO to store URL mappings
@Repository("UrlDao")
public class UrlDao implements IUrlDao{

    private Map<String, URL> inMemoryDB = new ConcurrentHashMap<>();

    /**
     * Added url mapping in a concurrentHashMap. The key for map is hashcode and the value is URL object
     * @param hashcode
     * @param url
     */
    @Override
    public void addUrlMapping(String hashcode, URL url) {
        inMemoryDB.put(hashcode, url);
    }

    /**
     * Fetch the URL for given hashcode. Return null if key not found
     * @param hashcode
     * @return URL
     */
    @Override
    public URL getUrlFromHashcode(String hashcode) {
        return inMemoryDB.get(hashcode);
    }
}
