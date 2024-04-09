package com.prabhav.LibraryManagement.Service;

import com.prabhav.LibraryManagement.Entity.Book;
import com.prabhav.LibraryManagement.Entity.User;
import com.prabhav.LibraryManagement.Repository.BookRepository;
import com.prabhav.LibraryManagement.Repository.UserRepository;
import com.prabhav.LibraryManagement.Service.security.UserDetailsCustom;
import com.prabhav.LibraryManagement.request.UserDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

//    public List<Book> searchBooks(String keyword) {
//        if (keyword != null) {
//            return bookRepository.search(keyword);
//        }
//        return bookRepository.findAll();
//    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) throws ChangeSetPersister.NotFoundException {
        var book = bookRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        bookRepository.deleteById(book.getId());
    }
    // Additional method to retrieve the username of the borrowing student
//    public String getBorrowerUsername(Long bookId) {
//        Book book = findById(bookId);
//        if (book != null && book.isBorrowed() && book.getBorrowedBy() != null) {
//            return book.getBorrowedBy().getUsername();  // Assuming username is a field in the User entity
//        }
//        return null;
//    }

    public Book borrowBook(Long bookId) {
        if (bookId == null) {
            System.out.println("bookId is null");
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            return null;
        }

        Book book = findById(bookId);
        System.out.println("Book: " + book);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);

        if (book != null && !book.isBorrowed() && authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetailsCustom) {
                UserDetailsCustom userDetails = (UserDetailsCustom) principal;
                System.out.println("loggedInUser: " + userDetails.getUsername());
                book.setBorrowedBy(userDetails);
                book.setBorrowed(true);
                return updateBook(book);
            }
            else {
                System.out.println("Unexpected principal type: " + principal.getClass());
            }
        }
        return null;
    }


    public Book returnBook(Long bookId) {
        Book book = findById(bookId);
        if (book != null && book.isBorrowed()) {
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            return updateBook(book);
        }
        return null;
    }

//    public List<Book> searchBoooks(String keyword) {
//        if (keyword != null) {
//            // Convert the keyword to Long if applicable
//            Long id = null;
//            try {
//                id = Long.parseLong(keyword);
//            } catch (NumberFormatException e) {
//                // Ignore parsing exception
//            }
//
//            return bookRepository.findByTitleContainingOrAuthorContainingOrIdContaining(keyword, keyword, id);
//        }
//        return bookRepository.findAll();
//    }


public List<Book> findByTitleContainingOrAuthorContaining(String searchTerm, String searchTerm1) {
    return bookRepository.findByTitleContainingOrAuthorContaining(searchTerm, searchTerm1);
}
}
