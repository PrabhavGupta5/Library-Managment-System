package com.prabhav.LibraryManagement.Controller;

import com.prabhav.LibraryManagement.Entity.Book;
import com.prabhav.LibraryManagement.Service.BookServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/search")
    public String searchBooks(@RequestParam String searchTerm, Model model) {
        List<Book> searchResults = bookService.findByTitleContainingOrAuthorContaining(
                searchTerm, searchTerm);
        model.addAttribute("books", searchResults);
        return "list-book";
    }

    @GetMapping("/add-book")
    public String showAddBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @GetMapping("/list-book")
    public String showBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "list-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/list-book";
    }

    @GetMapping(value = "/username")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
    @GetMapping("/borrow")
    public String borrowBook(RedirectAttributes redirectAttributes, @RequestParam(name = "bookId", required = true) Long bookId) {
        Book borrowedBook = bookService.borrowBook(bookId);

        if (borrowedBook != null) {
            redirectAttributes.addAttribute("message", "Successfuly assigned the book.");
           // return ResponseEntity.ok("Book borrowed successfully");
        } else {
            redirectAttributes.addAttribute("error", "Failed to borrow the book.");
           // ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to borrow the book");
        }
        return "redirect:/list-book";
    }

    @GetMapping("/return")
    public String returnBook(RedirectAttributes redirectAttributes,@RequestParam(name = "bookId", required = true) Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);

        if (returnedBook != null) {
            //return ResponseEntity.ok("Book returned successfully");
            redirectAttributes.addAttribute("message", "Successfuly returned the book.");

        } else {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to return the book");
            redirectAttributes.addAttribute("error", "Failed to return the book.");

        }
        return "redirect:/list-book";
    }

}


