package ua.vin.five.thymeleafMongoDB.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vin.five.thymeleafMongoDB.models.Book;

public interface BookRepository extends MongoRepository<Book, Long> {
//    Optional<Book> findBookByISBN(String ISBN);
}
