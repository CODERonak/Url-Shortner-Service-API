package com.project.URLShortner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.URLShortner.model.ShortUrl;

// Repository interface for ShortUrl entity

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    // Custom query method to find a ShortUrl by its alias
    Optional<ShortUrl> findByAlias(String alias);

    // Custom query method to check if a ShortUrl with the given alias exists
    boolean existsByAlias(String alias);

}
