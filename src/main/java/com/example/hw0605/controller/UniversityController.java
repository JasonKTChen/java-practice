package com.example.hw0605.controller;

import com.example.hw0605.Dto.CountryRequestDto;
import com.example.hw0605.Dto.UniversityDto;
import com.example.hw0605.service.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/universities")
    public List<UniversityDto> getUniversities(@RequestParam String name) {
        return universityService.searchUniversities(name);
    }

    @PostMapping("/universities/by-countries")
    public CompletableFuture<List<UniversityDto>> getUniversitiesByCountries(@RequestBody CountryRequestDto request) {
        List<CompletableFuture<List<UniversityDto>>> futures = request.getCountries().stream()
                .map(universityService::searchUniversitiesByCountry)
                .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .flatMap(future -> future.join().stream())
                        .collect(Collectors.toList()));
    }
}
