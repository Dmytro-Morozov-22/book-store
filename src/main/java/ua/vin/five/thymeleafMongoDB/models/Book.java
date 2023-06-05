package ua.vin.five.thymeleafMongoDB.models;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;


@Data
@Document(collection = "Book")
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    @NotEmpty(message = "Please put the book title")
    private String bookTitle;

    @NotEmpty(message = "Please put the author's name and surname")
    private String author;

    @NotEmpty(message = "Please put the language")
    private String language;

    @NotNull(message = "Please put a value more than zero")
    @Positive(message = "Please put a positive value")
    private Integer pages;

    @NotNull(message = "Please put a value more than zero")
    @Positive(message = "Please put a positive value of the rate")
    private Double monthlyRent;

    @NotEmpty(message = "Please choose the status of availability")
    private String bookAvailability;

//    @Size(min = 2, max = 4, message = "Please put four digits")
//    @Min(value = 4, message = "Please put four digits min")
//    @Max(value = 6, message = "Please put four digits max")
    @Positive
    private Integer publishYear;
    private String ISBN;

    public Book() {}

    public Book(String bookTitle, String author, String language, Integer pages, Double monthlyRent, String bookAvailability) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.language = language;
        this.pages = pages;
        this.bookAvailability = bookAvailability;
        this.monthlyRent = monthlyRent;
    }

    public Book(String bookTitle, String author, String language, Integer pages, Double monthlyRent, String bookAvailability, Integer publishYear, String ISBN) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.language = language;
        this.pages = pages;
        this.bookAvailability = bookAvailability;
        this.monthlyRent = monthlyRent;
        this.publishYear = publishYear;
        this.ISBN = ISBN;
    }
}
//    @Indexed(unique = true)     //вимагає від поля унікального значення