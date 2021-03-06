package net.jsrois.libritos.controllers;

import net.jsrois.libritos.models.Book;
import net.jsrois.libritos.services.BookCoverService;
import net.jsrois.libritos.services.BookService;
import net.jsrois.libritos.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private BookCoverService bookCoverService;
    private CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, BookCoverService bookCoverService, CategoryService categoryService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    String listBooks(Model model, @RequestParam(required = false) Long categoryId) {

        List<Book> books;
        if (categoryId == null) {
            books = bookService.allBooks();
        } else {
            books = categoryService.getCategory(categoryId).getBooks();
        }

        model.addAttribute("title", "Book list");
        model.addAttribute("books", books);
        model.addAttribute("categories", categoryService.allCategories());
        return "books/all";
    }

    @GetMapping("/new")
    String getForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("title", "Create new book");
        model.addAttribute("categories", categoryService.allCategories());
        return "books/new";
    }

    @PostMapping("/new")
    String addBook(
            @ModelAttribute Book book,
            @RequestParam("image")MultipartFile imageFile ) throws IOException {

        String imageName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        book.setImageName(imageName);
        Long id = bookService.save(book).getId();

        bookCoverService.saveBookCover(id, imageName, imageFile);

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
