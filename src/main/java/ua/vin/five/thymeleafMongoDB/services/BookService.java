package ua.vin.five.thymeleafMongoDB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import ua.vin.five.thymeleafMongoDB.models.Book;
import ua.vin.five.thymeleafMongoDB.repository.BookRepository;

import javax.servlet.http.HttpServletRequest;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void updateEntity(long id, HttpServletRequest param, Integer publishYear, String ISBN){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setBookTitle(param.getParameter("bookTitle"));
        book.setAuthor(param.getParameter("author"));
        book.setLanguage(param.getParameter("language"));
        book.setPages(Integer.valueOf(param.getParameter("pages")));
        book.setMonthlyRent(Double.valueOf(param.getParameter("monthlyRent")));
        book.setBookAvailability(param.getParameter("bookAvailability"));
        book.setPublishYear(publishYear);
        book.setISBN(ISBN);
        bookRepository.save(book);

    }

    public void update(long id, Book book, Integer publishYear, String ISBN){
        Book updatedBook = bookRepository.findById(id).orElseThrow();
        updatedBook.setBookTitle(book.getBookTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setLanguage(book.getLanguage());
        updatedBook.setPages(Integer.valueOf(book.getPages()));
        updatedBook.setMonthlyRent(Double.valueOf(book.getMonthlyRent()));
        updatedBook.setBookAvailability(book.getBookAvailability());
        updatedBook.setPublishYear(publishYear);
        updatedBook.setISBN(ISBN);
        bookRepository.save(updatedBook);
    }





}
