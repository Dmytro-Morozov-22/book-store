package ua.vin.five.thymeleafMongoDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.vin.five.thymeleafMongoDB.models.Book;
import ua.vin.five.thymeleafMongoDB.repository.BookRepository;
import ua.vin.five.thymeleafMongoDB.services.BookService;
import ua.vin.five.thymeleafMongoDB.services.SequenceGeneratorService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    BookRepository repository;

    @Autowired
    BookService bookService;


    @GetMapping
    public String getBook(Model model){
        model.addAttribute("item", "This is our great library");
        return "index";
    }

    @GetMapping("/store")
    public String getAllIssues(Model model){
        model.addAttribute("storeKey", repository.findAll());
        return "libraryStore";
    }




    @GetMapping("/addBook")
    public String addBook(@ModelAttribute("book") Book book){
//        model.addAttribute("book", new Book());
        return "addBookForm";
    }

    @PostMapping("/addBook")
    public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){

        System.out.println(book);

        if(bindingResult.hasErrors()) return "addBookForm";
        repository.save(book);
        return "redirect:/store";
    }

    @GetMapping ("/delete/{id}")
    public String deleteBook(@PathVariable long id){
        repository.deleteById(id);
        return "redirect:/store";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable(value = "id") long id, Model model){
        Optional<Book> book = repository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        return "editBookForm";
    }

    @PatchMapping("/edit/{id}")
    public String updateBook(@PathVariable(value = "id") long id, HttpServletRequest param, @RequestParam(value = "publishYear", required = false) Integer publishYear, @RequestParam(value = "ISBN", required = false) String ISBN, Model model ) {

//        if(bindingResult.hasErrors()) return "editBookForm";

        bookService.updateEntity(id, param, publishYear, ISBN);
        return "redirect:/store";
    }

//    @PatchMapping("/edit/{id}")
//    public String updateBook1(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,  @PathVariable(value = "id") long id, @RequestParam(value = "publishYear", required = false) Integer publishYear, @RequestParam(value = "ISBN", required = false) String ISBN ) {
//
//        if(bindingResult.hasErrors()) return "editBookForm";
//
//        bookService.update(id, book, publishYear, ISBN);
//        return "redirect:/store";
//    }










//    @PostMapping("/edit/{id}")
//    public String updateBook(@PathVariable(value = "id") int id,
//                             @RequestParam String bookTitle,
//                             @RequestParam String author,
//                             @RequestParam String ISBN,
//                             @RequestParam String language,
//                             @RequestParam Integer pages,
//                             @RequestParam Double monthlyRent,
//                             @RequestParam String bookAvailability,
//                             @RequestParam Integer publishYear,

//                             Model model ) {
//        Book book = repository.findById(id).orElseThrow();
//        book.setBookTitle(bookTitle);
//        book.setAuthor(author);
//        book.setLanguage(language);
//        book.setPages(pages);
//        book.setMonthlyRent(monthlyRent);
//        book.setBookAvailability(bookAvailability);
//        book.setPublishYear(publishYear);
//        book.setISBN(ISBN);
//
//        repository.save(book);
//        return "redirect:/store";
//    }

//    @PostMapping("/edit/{id}")
//    public String updateBook(@PathVariable(value = "id") int id, @RequestBody Book b, Model model ) {
//        Book book = repository.findById(id).orElseThrow();
//        book.setBookTitle(b.getBookTitle());
//        book.setAuthor(b.getAuthor());
//        book.setLanguage(b.getLanguage());
//        book.setPages(b.getPages());
//        book.setMonthlyRent(b.getMonthlyRent());
//        book.setBookAvailability(b.getBookAvailability());
//        book.setPublishYear(b.getPublishYear());
//        book.setISBN(b.getISBN());
//
//        repository.save(book);
//        return "redirect:/store";
//    }


//    WORKING VERSION
//    @PostMapping("/edit/{id}")
//    public String updateBook(@PathVariable(value = "id") long id, HttpServletRequest b, @RequestParam(value = "publishYear", required = false) Integer publishYear, @RequestParam(value = "ISBN", required = false) String ISBN, Model model ) {
//        Book book = repository.findById(id).orElseThrow();
//        book.setBookTitle(b.getParameter("bookTitle"));
//        book.setAuthor(b.getParameter("author"));
//        book.setLanguage(b.getParameter("language"));
//        book.setPages(Integer.valueOf(b.getParameter("pages")));
//        book.setMonthlyRent(Double.valueOf(b.getParameter("monthlyRent")));
//        book.setBookAvailability(b.getParameter("bookAvailability"));
//        book.setPublishYear(publishYear);
//        book.setISBN(ISBN);
//        repository.save(book);
//        return "redirect:/store";
//    }










//    @PostMapping("/addBook")
//    public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()) return "addBookForm";
//        repository.save(book);
//        return "redirect:/store";
//    }






}
