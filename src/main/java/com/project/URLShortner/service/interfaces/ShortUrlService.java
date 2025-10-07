package com.project.URLShortner.service.interfaces;

import com.project.URLShortner.dto.shorten.*;

// Interface methods for the ShortUrlService
public interface ShortUrlService {

    // creates a shortened URL
    ShortenUrlResponse createShortenUrlResponse(ShortenUrlRequest dto);

    // resolves a shortened URL by its alias
    String resolveAlias(String alias);

    // increments the click count for a shortened URL
    void incrementClickCount(String alias);
}
