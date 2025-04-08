package com.brs.bookrentalsystem.service;
import org.springframework.stereotype.Service;

import com.brs.bookrentalsystem.dto.CategoryDistributionDto;
import com.brs.bookrentalsystem.dto.OverdueBooksDto;
import com.brs.bookrentalsystem.dto.RentalTrendsDto;
import com.brs.bookrentalsystem.mapper.ReportMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final ReportMapper reportMapper;

    public ReportService(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    public RentalTrendsDto getRentalTrends() {
        List<Map<String, Object>> results = reportMapper.getRentalTrends();
        RentalTrendsDto dto = new RentalTrendsDto();
        List<String> months = new ArrayList<>();
        List<Long> counts = new ArrayList<>();

        for (Map<String, Object> result : results) {
            months.add((String) result.get("month"));
            counts.add(((Number) result.get("count")).longValue());
        }

        dto.setMonths(months);
        dto.setCounts(counts);
        return dto;
    }

    public CategoryDistributionDto getCategoryDistribution() {
        List<Map<String, Object>> results = reportMapper.getCategoryDistribution();
        CategoryDistributionDto dto = new CategoryDistributionDto();
        List<String> categories = new ArrayList<>();
        List<Long> counts = new ArrayList<>();

        for (Map<String, Object> result : results) {
            categories.add((String) result.get("category"));
            counts.add(((Number) result.get("count")).longValue());
        }

        dto.setCategories(categories);
        dto.setCounts(counts);
        return dto;
    }

    public OverdueBooksDto getOverdueBooks() {
        List<Map<String, Object>> results = reportMapper.getOverdueBooks();
        OverdueBooksDto dto = new OverdueBooksDto();
        List<String> days = new ArrayList<>();
        List<Long> counts = new ArrayList<>();

        for (Map<String, Object> result : results) {
            days.add(result.get("days_overdue") + " days");
            counts.add(((Number) result.get("count")).longValue());
        }

        dto.setDays(days);
        dto.setCounts(counts);
        return dto;
    }
}