package com.example.hw0605.service;

import com.example.hw0605.Dto.UniversityDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    private final RestTemplate restTemplate;

    public UniversityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UniversityDto> searchUniversities(String name) {
        URI uri = UriComponentsBuilder.fromUriString("http://universities.hipolabs.com/search")
                .queryParam("name", name)
                .build()
                .toUri();

        UniversityDto[] response = restTemplate.getForObject(uri, UniversityDto[].class);

        return Arrays.stream(response)
                .map(university -> {
                    UniversityDto dto = new UniversityDto();
                    dto.setName(university.getName());
                    dto.setWebPages(university.getWebPages());
                    if (university.getWebPages() != null && !university.getWebPages().isEmpty()) {
                        dto.setDomain(university.getWebPages().get(0).replaceFirst("http(s)?://(www\\.)?", ""));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public CompletableFuture<List<UniversityDto>> searchUniversitiesByCountry(String country) {
        return CompletableFuture.supplyAsync(() -> {
            URI uri = UriComponentsBuilder.fromUriString("http://universities.hipolabs.com/search")
                    .queryParam("country", country)
                    .build()
                    .toUri();

            UniversityDto[] response = restTemplate.getForObject(uri, UniversityDto[].class);

            return Arrays.stream(response)
                    .map(university -> {
                        UniversityDto dto = new UniversityDto();
                        dto.setName(university.getName());
                        dto.setWebPages(university.getWebPages());
                        if (university.getWebPages() != null && !university.getWebPages().isEmpty()) {
                            dto.setDomain(university.getWebPages().get(0).replaceFirst("http(s)?://(www\\.)?", ""));
                        }
                        return dto;
                    })
                    .collect(Collectors.toList());
        });
    }
}
