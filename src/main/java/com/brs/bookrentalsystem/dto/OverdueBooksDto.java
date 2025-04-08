package com.brs.bookrentalsystem.dto;

import java.util.List;

public class OverdueBooksDto {
    private List<String> days;
    private List<Long> counts;

    public List<String> getDays() { return days; }
    public void setDays(List<String> days) { this.days = days; }
    public List<Long> getCounts() { return counts; }
    public void setCounts(List<Long> counts) { this.counts = counts; }
}