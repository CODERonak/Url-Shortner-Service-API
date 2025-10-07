package com.project.URLShortner.dto.shorten;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO for shortening a URL
@Data
public class ShortenUrlRequest {
    // Target url to be shortened
    @NotBlank
    private String targetUrl;
}
