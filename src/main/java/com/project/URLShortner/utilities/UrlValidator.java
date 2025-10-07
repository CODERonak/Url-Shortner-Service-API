package com.project.URLShortner.utilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

// A utility class for validating URLs
public class UrlValidator {

    // Checks if a given URL is valid
    // Returns true if the URL is valid, false otherwise
    public static boolean isValid(String url) {
        try {
            new URI(url).toURL();
            return true;
        } catch (URISyntaxException | MalformedURLException e) {
            return false;
        }
    }
}
