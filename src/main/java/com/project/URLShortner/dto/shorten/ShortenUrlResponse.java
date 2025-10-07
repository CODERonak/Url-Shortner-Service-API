package com.project.URLShortner.dto.shorten;

import java.time.Instant;

import lombok.Data;

// DTO for the response of a URL shortening request.

@Data
public class ShortenUrlResponse {
    // The unique alias generated for the shortened URL.
    private String alias;

    // The full URL that was shortened.
    private String shortUrl;

    // The original URL that was shortened.
    private String targetUrl;

    // The date and time when the URL was created.
    private Instant createdAt;
}
