package net.jsrois.springbootusercrud.controllers;

import net.jsrois.springbootusercrud.models.Book;
import net.jsrois.springbootusercrud.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    String listBooks(Model model) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("title", "Book list");
        model.addAttribute("books", books);
        return "books/all";
    }

    @GetMapping("/new")
    String getForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("title", "Create new book");
        return "books/new";
    }

    @PostMapping("/new")
    String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    String editBook(Model model, @PathVariable Long id){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("title", "Edit book");
        return "books/new";
    }

    @GetMapping("/delete/{id}")
    String removeBook(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/books";
    }
}
