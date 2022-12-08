package com.libraryproject.librarysystem.controllers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.domain.DTO.BooksDTO;
import com.libraryproject.librarysystem.domain.factories.interfaces.IBooksFactory;
import com.libraryproject.librarysystem.repositories.AuthorsRepository;
import com.libraryproject.librarysystem.repositories.BooksRepository;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import com.libraryproject.librarysystem.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class BooksControllers {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private IBooksFactory booksFactory;


    @GetMapping("/addnewbook")
    public String bookList(Model model) {

        model.addAttribute("authors", authorsRepository.findAll());
        model.addAttribute("genre", Genre.values());
        model.addAttribute("currentYear", new Date().getYear()+1900);

        return "addnewbook.html";
    }


    @GetMapping("/addthisnewbook")
    public String addBook(Model model, @RequestParam(value = "title") String title,
                          @RequestParam(value = "url") String url,
                          @RequestParam(value = "author") String authorID,
                          @RequestParam(value = "genre") String genre,
                          @RequestParam(value = "isbn") String isbn,
                          @RequestParam(value = "releaseYear") String releaseYear,
                          @RequestParam(value = "description") String description) {
        if(title.length()<1 || title.length()>100) {
            model.addAttribute("errorMessage", "The book title should be at least 1 symbol and no more than 100 symbols long.");
            return "error.html";
        }

        try {
            Genre.valueOf(genre);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "The book record should have a genre, that is defined in the system.");
            return "error.html";
        }

        if(isbn.length() < 10 || isbn.length() > 18 || !(isbn.matches("[0123456789-]+"))) {
            model.addAttribute("errorMessage", "The book's ISBN code should be between 10 and 18 symbols and be made up of numbers and dashes.");
            return "error.html";
        }

        try {
            int temporaryValue = Integer.parseInt(releaseYear);
            if(temporaryValue < 1 )
                throw new Exception();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "The book's year of release should be a positive integer.");
            return "error.html";
        }

        if(Integer.parseInt(releaseYear) > Calendar.getInstance().get(Calendar.YEAR)) {
            model.addAttribute("errorMessage", "The book's year of release shouldn't greater than the current year.");
            return "error.html";
        }

        if(description.length() > 500) {
            model.addAttribute("errorMessage", "The book's description should not be longer than 500 characters.");
            return "error.html";
        }

        Optional<Authors> authorObject = authorsRepository.findById(Integer.parseInt(authorID.trim()));
        if(authorObject.isEmpty()) {
            model.addAttribute("errorMessage", "The chosen author does not exist in the database.");
            return "error.html";
        }

        if(booksRepository.findAll().stream().filter(b -> Objects.equals(b.getIsbn(), isbn)).findFirst().orElse(null) != null){
            model.addAttribute("errorMessage", "Book with this ISBN already exists. Record not created.");
            return "error.html";
        }

        Books book = new Books(title, Genre.valueOf(genre), isbn, releaseYear, description, url);
        Authors author = authorObject.get();
        List<Books> listForAuthor = new ArrayList<>();
        listForAuthor.add(book);
        author.setBooksList(listForAuthor);
        List<Authors> authorsList = new ArrayList<>();
        authorsList.add(author);
        book.setAuthorsList(authorsList);
        book.setAvailability(Availability.AVAILABLE);
        Books savedBook = booksRepository.save(book);

        return "redirect:/bookslist";
    }

    @GetMapping("/viewbook/{id}")
    public String viewOneBook(Model model, @PathVariable int id) {
        Books book = booksRepository.getById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails currentUser = (MyUserDetails) authentication.getPrincipal();
        Users user = usersRepository.getById(currentUser.getUserID());

        if (user.getAccessLevel() == AccessLevel.LIBRARIAN) {
            System.out.println("It's librarian " + currentUser);
            model.addAttribute("level","librarian");
        } else {
//            System.out.println("It's user " + currentUser);
            model.addAttribute("level","user");
        }


        model.addAttribute("book", book);
        return "infoonebook.html";
    }

    @GetMapping("/viewbook/delete/{id}")
    public String deleteBook(@PathVariable int id) {
//        System.out.println("Trying to delete this book: " + id );
        booksRepository.deleteById(id);

        return "redirect:/bookslist";
    }

    @GetMapping("/viewbook/edit/{id}")
    public String editBook(Model model,@PathVariable int id) {
        Books book = booksRepository.getById(id);
        model.addAttribute("book", book);
        return "editbook.html";
    }

    @PostMapping("/editthisbook")
    public String editThisBook(@Valid BooksDTO booksDTO, Model model) {
        Books book = booksFactory.create(booksDTO);
        booksRepository.save(book);
        book = booksRepository.getById(book.getId());
        model.addAttribute("book", book);
        return "redirect:/bookslist";
    }

}
