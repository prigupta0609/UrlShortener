package com.padlet.dao;

import com.padlet.model.URL;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// In-memory DAO to store URL mappings
@Repository("UrlDao")
public class UrlDao implements IUrlDao{

    private Map<String, URL> inMemoryDB = new ConcurrentHashMap<>();

    @Override
    public void addUrlMapping(String hashcode, URL url) {
        inMemoryDB.put(hashcode, url);
    }

    @Override
    public URL getUrlFromHashcode(String hashcode) {
        return inMemoryDB.get(hashcode);
    }
}
