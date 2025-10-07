package com.project.URLShortner.utilities;

// A utility class for encoding long integers to base62 strings
public class Base62Encoder {

    // Encodes a long integer to a base62 string
    // Returns the encoded string
    public static String encode(long num) {
        final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(chars.charAt((int) (num % 62)));
            num /= 62;
        }
        return sb.reverse().toString();
    }
}
