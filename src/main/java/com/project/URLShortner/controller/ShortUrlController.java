package com.project.URLShortner.controller;

import lombok.AllArgsConstructor;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.URLShortner.dto.shorten.*;
import com.project.URLShortner.service.interfaces.ShortUrlService;

import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/url/")
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    // Endpoint for creating a new shortened URL
    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> createUrl(@RequestBody @Valid ShortenUrlRequest request) {
        ShortenUrlResponse response = shortUrlService.createShortenUrlResponse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint for resolving a shortened URL
    @GetMapping("/{alias}")
    public ResponseEntity<Void> redirectToTarget(@PathVariable String alias) {
        String targetUrl = shortUrlService.resolveAlias(alias);

        // Redirect to the target URL
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(targetUrl));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
