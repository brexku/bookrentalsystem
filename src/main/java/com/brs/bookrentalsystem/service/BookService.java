package com.brs.bookrentalsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brs.bookrentalsystem.dto.Book;
import com.brs.bookrentalsystem.mapper.BookMapper;

@Service
public class BookService {

    @Autowired
    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookMapper.getAllBooks();
        books.forEach(book -> {
            if (book.getBookStatus() == null || book.getBookStatus().trim().isEmpty()) {
                book.setBookStatus("available");
            }
        });
        return books;
    }

    public Book getBookById(int bookId) {
        return bookMapper.getBookById(bookId);
    }

    public void insertBook(Book book) {
        if (book.getBookStatus() == null) {
            book.setBookStatus("available");
        }
        bookMapper.insertBook(book);
    }

    public void updateBook(int bookId, Book book) {
        book.setBookId(bookId);
        bookMapper.updateBook(book);
    }

    public void deleteBook(int bookId) {
        Book book = bookMapper.getBookById(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        String bookStatus = book.getBookStatus() != null ? book.getBookStatus().trim() : null;
        if ("rented".equalsIgnoreCase(bookStatus)) {
            throw new RuntimeException("You can't delete a rented book.");
        }
        bookMapper.deleteBook(bookId);
    }

}
