package com.project.URLShortner.service.implementations;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.project.URLShortner.dto.shorten.ShortenUrlRequest;
import com.project.URLShortner.dto.shorten.ShortenUrlResponse;
import com.project.URLShortner.mapper.ShortUrlMapper;
import com.project.URLShortner.model.ShortUrl;
import com.project.URLShortner.repository.ShortUrlRepository;
import com.project.URLShortner.service.interfaces.ShortUrlService;
import com.project.URLShortner.utilities.Base62Encoder;
import com.project.URLShortner.utilities.UrlValidator;

import lombok.AllArgsConstructor;

// Implementation of the ShortUrlService interface
@Service
@AllArgsConstructor
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlMapper shortUrlMapper;

    // Base URL for the shortened URLs
    // The url doesnt work, but the resolve yourlocalhost/api/url/{alias}
    // does work if your running on localhost
    private static final String BASE_URL = "https://wel.ly/";

    // creates a new shortened url and saves it to the database
    // returns the shortened url
    @Override
    public ShortenUrlResponse createShortenUrlResponse(ShortenUrlRequest request) {
        // validates the url and generates a unique alias if valid
        if (!UrlValidator.isValid(request.getTargetUrl())) {
            throw new IllegalArgumentException("Invalid URL: " + request.getTargetUrl());
        }

        // generates a unique alias and saves the url to the database
        String alias = generateUniqueAlias();

        // creates a new ShortUrl entity and saves it to the database
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setAlias(alias);
        shortUrl.setTargetUrl(request.getTargetUrl());
        ShortUrl saved = shortUrlRepository.save(shortUrl);

        // creates a ShortenUrlResponse DTO and returns it
        ShortenUrlResponse responseDto = shortUrlMapper.toDto(saved);
        responseDto.setShortUrl(BASE_URL + saved.getAlias());
        responseDto.setCreatedAt(saved.getCreatedAt());

        return responseDto;
    }

    // Resolves a shortened URL by its alias and increments the click count
    @Override
    public String resolveAlias(String alias) {
        ShortUrl shortUrl = shortUrlRepository.findByAlias(alias)
                .orElseThrow(() -> new RuntimeException("Alias not found"));

        if (!shortUrl.getIsActive()) {
            throw new RuntimeException("URL is inactive or expired");
        }

        incrementClickCount(alias);

        return shortUrl.getTargetUrl();
    }

    // Increments the click count for a shortened URL
    @Override
    public void incrementClickCount(String alias) {
        ShortUrl shortUrl = shortUrlRepository.findByAlias(alias)
                .orElseThrow(() -> new RuntimeException("Alias not found"));

        shortUrl.setClickCount(shortUrl.getClickCount() + 1);
        shortUrlRepository.save(shortUrl);
    }

    // Generates a unique alias for the shortened URL
    private String generateUniqueAlias() {
        String alias;
        do {
            alias = Base62Encoder.encode(ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE));
        } while (shortUrlRepository.existsByAlias(alias));
        return alias;
    }

}
