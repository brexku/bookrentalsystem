package com.brs.bookrentalsystem.dto;
import java.util.List;

public class RentalTrendsDto {
    private List<String> months;
    private List<Long> counts;

    public List<String> getMonths() { return months; }
    public void setMonths(List<String> months) { this.months = months; }
    public List<Long> getCounts() { return counts; }
    public void setCounts(List<Long> counts) { this.counts = counts; }
}
