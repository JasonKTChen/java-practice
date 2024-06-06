package com.example.hw0605.Dto;
import java.util.List;

public class UniversityDto {
    private String name;
    private List<String> webPages;
    private String domain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<String> webPages) {
        this.webPages = webPages;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
