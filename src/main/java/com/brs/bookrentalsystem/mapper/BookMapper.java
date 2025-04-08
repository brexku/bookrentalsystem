package com.brs.bookrentalsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.brs.bookrentalsystem.dto.Book;

@Mapper
public interface BookMapper {

        @Select("Select * FROM books")
        List<Book> getAllBooks();

        @Select("SELECT * FROM books WHERE book_id = #{bookId}")
        Book getBookById(@Param("bookId") int bookId);

        @Insert(" INSERT INTO books (title, author, isbn, book_category, edition, date_published, book_status)"
                        + "VALUES (#{title}, #{author}, #{isbn}, #{bookCategory}, #{edition}, #{datePublished}, #{bookStatus}) ")
        void insertBook(Book book);

        @Update("UPDATE books SET title = #{title}, author = #{author}, isbn = #{isbn}, book_category = #{bookCategory}, "
                        + "edition = #{edition}, date_published = #{datePublished}, book_status = #{bookStatus} "
                        + "WHERE book_id = #{bookId}")
        void updateBook(Book book);

        void updateBookQuantity(@Param("bookId") int bookId, @Param("quantity") int quantity);

        @Delete("DELETE FROM books WHERE book_id = #{bookId}")
        void deleteBook(int bookId);
}
