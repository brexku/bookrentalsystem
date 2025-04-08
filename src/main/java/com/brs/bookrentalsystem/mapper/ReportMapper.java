package com.brs.bookrentalsystem.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    @Select("SELECT MONTHNAME(r.rental_date) as month, COUNT(*) as count " +
            "FROM rentals r " +
            "WHERE YEAR(r.rental_date) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(r.rental_date), MONTHNAME(r.rental_date) " +
            "ORDER BY MONTH(r.rental_date)")
    List<Map<String, Object>> getRentalTrends();

    @Select("SELECT c.name as category, COUNT(r.id) as count " +
            "FROM rentals r " +
            "JOIN books b ON r.book_id = b.id " +
            "JOIN categories c ON b.category_id = c.id " +
            "GROUP BY c.id, c.name")
    List<Map<String, Object>> getCategoryDistribution();

    @Select("SELECT DATEDIFF(CURRENT_DATE, r.due_date) as days_overdue, COUNT(*) as count " +
            "FROM rentals r " +
            "WHERE r.return_date IS NULL AND r.due_date < CURRENT_DATE " +
            "GROUP BY DATEDIFF(CURRENT_DATE, r.due_date) " +
            "ORDER BY days_overdue")
    List<Map<String, Object>> getOverdueBooks();
}