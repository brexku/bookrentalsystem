package com.brs.bookrentalsystem.dto;

import java.util.List;

public class CategoryDistributionDto {
    private List<String> categories;
    private List<Long> counts;

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
    public List<Long> getCounts() { return counts; }
    public void setCounts(List<Long> counts) { this.counts = counts; }
}