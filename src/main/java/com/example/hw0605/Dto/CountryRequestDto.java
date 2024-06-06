package com.example.hw0605.Dto;

import java.util.List;

public class CountryRequestDto {
    private List<String> countries;
    public List<String> getCountries() {
        return countries;
    }
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
}
