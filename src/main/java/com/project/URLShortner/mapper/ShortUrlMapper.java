package com.project.URLShortner.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.URLShortner.dto.shorten.*;
import com.project.URLShortner.model.ShortUrl;

// A mapper interface for converting between ShortUrl and ShortenUrlRequest/Response DTOs
@Mapper(componentModel = "spring")
public interface ShortUrlMapper {

    // Maps a ShortenUrlRequest DTO to a ShortUrl entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alias", ignore = true)
    @Mapping(target = "clickCount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    ShortUrl toEntity(ShortenUrlRequest dto);

    // Maps a ShortUrl entity to a ShortenUrlResponse DTO
    @Mapping(target = "alias", source = "alias")
    @Mapping(target = "shortUrl", expression = "java(entity.getTargetUrl() + '/' + entity.getAlias())")
    @Mapping(target = "createdAt", source = "createdAt")
    ShortenUrlResponse toDto(ShortUrl entity);
}
