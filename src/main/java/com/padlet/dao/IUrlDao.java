package com.padlet.dao;

import com.padlet.model.URL;

public interface IUrlDao {

    public void addUrlMapping(String hashcode, URL url);
    public URL getUrlFromHashcode(String hashcode);
}
