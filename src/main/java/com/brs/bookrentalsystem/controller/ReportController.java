package com.brs.bookrentalsystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brs.bookrentalsystem.dto.CategoryDistributionDto;
import com.brs.bookrentalsystem.dto.OverdueBooksDto;
import com.brs.bookrentalsystem.dto.RentalTrendsDto;
import com.brs.bookrentalsystem.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/rental-trends")
    public RentalTrendsDto getRentalTrends() {
        return reportService.getRentalTrends();
    }

    @GetMapping("/category-distribution")
    public CategoryDistributionDto getCategoryDistribution() {
        return reportService.getCategoryDistribution();
    }

    @GetMapping("/overdue-books")
    public OverdueBooksDto getOverdueBooks() {
        return reportService.getOverdueBooks();
    }
}