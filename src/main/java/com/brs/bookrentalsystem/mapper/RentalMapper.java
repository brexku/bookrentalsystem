package com.brs.bookrentalsystem.mapper;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.*;

import com.brs.bookrentalsystem.dto.Rental;

@Mapper
public interface RentalMapper {

        @Select("SELECT r.*,b.title,c.cust_fname+' '+ (c.cust_mname+' '+c.cust_lname) AS FullName FROM rentals r \r\n" + //
                        "inner join books b on b.book_id=r.book_id inner join customers c on r.cust_id=c.cust_id")
        List<Rental> getAllRentals();

        @Select("SELECT * FROM rentals WHERE rental_id = #{rentalId}")
        Rental getRentalById(int rentalId);

        @Insert("INSERT INTO rentals (book_id, cust_id, rented_date, return_date, rental_duration, rental_price, overdue_fee, total_fee, rental_status) "
                        + "VALUES (#{bookId}, #{custId}, #{rentedDate}, #{returnDate}, #{rentalDuration}, #{rentalPrice}, #{overDueFee}, #{totalFee}, #{rentalStatus})")
        @Options(useGeneratedKeys = true, keyProperty = "rentalId")
        void rentBook(Rental rental);

        @Update("UPDATE rentals SET rented_date = #{rentedDate}, " +
                        "return_date = #{returnDate}, " +
                        "overdue_fee = #{overDueFee}, " +
                        "total_fee = #{totalFee}, " +
                        "rental_status = #{rentalStatus} " +
                        "WHERE rental_id = #{rentalId}")
        void updateRental(Rental rental);

        @Delete("DELETE FROM rentals WHERE rental_id = #{rentalId} AND rental_status = 'inactive'")
        void deleteRentalById(int rentalId);

        @Update("UPDATE books SET book_status = 'rented' WHERE book_id = #{bookId}")
        void markBookAsRented(int bookId);

        @Update("UPDATE books SET book_status = 'available' WHERE book_id = #{bookId}")
        void markBookAsReturned(int bookId);

        @Select("SELECT * FROM rentals WHERE cust_id = #{custId}")
        List<Rental> getRentalsByCustomerId(int custId);

        @Select("SELECT TOP 1 * FROM rentals WHERE book_id = #{bookId} ORDER BY rented_date DESC")
        Rental getLatestRentalByBookId(int bookId);

        @Update("UPDATE rentals SET rental_status = 'inactive' WHERE rented_date < #{oneYearAgo} AND rental_status != 'inactive'")
        void inactivateOldRentals(@Param("oneYearAgo") LocalDate oneYearAgo);
}