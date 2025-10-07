package com.project.URLShortner.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Data;

/*
 * This entity represents a shortened URL.
 * 
 * This model stores the mapping between short alias
 * and the original target URL along with the creation
 * date and other metadata.
 */

@Entity
@Data
@Table(name = "shorten_url")
public class ShortUrl {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique identifier for the shortened URL
    @Column(unique = true, length = 32)
    private String alias;

    // The original target URL
    @Column(nullable = false, columnDefinition = "TEXT")
    private String targetUrl;

    // The date and time when the url created
    @Column(nullable = false)
    private Instant createdAt;

    // The date and time when the url expires
    private Boolean isActive;

    // The number of times the url has been clicked
    private Long clickCount;

    // This method is called before the entity is persisted
    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        if (isActive == null)
            this.isActive = true;
        if (clickCount == null)
            this.clickCount = 0L;
    }

}
