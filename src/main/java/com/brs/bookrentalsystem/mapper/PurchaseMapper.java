package com.brs.bookrentalsystem.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.brs.bookrentalsystem.dto.Purchase;
@Mapper
public interface PurchaseMapper {

    @Insert("INSERT INTO purchases(book_title, isbn, quantity, price, purchase_date) " +
            "VALUES(#{bookTitle}, #{isbn}, #{quantity}, #{price}, #{purchaseDate})")
    @Options(useGeneratedKeys = true, keyProperty = "purchaseId")
    void insertPurchase(Purchase purchase);

    @Update("UPDATE purchases SET book_title=#{bookTitle}, isbn=#{isbn}, " +
            "quantity=#{quantity}, price=#{price}, purchase_date=#{purchaseDate} " +
            "WHERE purchase_id=#{purchaseId}")
    int updatePurchase(Purchase purchase);

    @Delete("DELETE FROM purchases WHERE purchase_id=#{purchaseId}")
    void deletePurchase(Long purchaseId);

    @Select("SELECT * FROM purchases WHERE purchase_id=#{purchaseId}")

    Optional<Purchase> findById(Long purchaseId);

    @Select("SELECT * FROM purchases ORDER BY purchase_date DESC")
    List<Purchase> findAll();

    @Select("SELECT * FROM purchases WHERE isbn LIKE CONCAT('%', #{isbn}, '%')")
    List<Purchase> findByIsbn(@Param("isbn") String isbn);
}
