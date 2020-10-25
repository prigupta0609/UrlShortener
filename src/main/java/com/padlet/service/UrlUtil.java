package com.padlet.service;

// This class contain the utility methods for URL
public class UrlUtil {

    private static final String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Generate Base62 hashcode for short url
     * @param value
     * @return
     */
    public static String getBase62(long value) {
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(str.charAt((int)(value % 62)));
            value /= 62;
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }
}
