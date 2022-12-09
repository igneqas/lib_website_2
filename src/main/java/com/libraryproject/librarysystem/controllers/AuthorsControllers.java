package com.libraryproject.librarysystem.controllers;


import com.libraryproject.librarysystem.domain.AccessLevel;
import com.libraryproject.librarysystem.domain.Authors;
import com.libraryproject.librarysystem.domain.DTO.AuthorsDTO;
import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.domain.factories.interfaces.IAuthorsFactory;
import com.libraryproject.librarysystem.repositories.AuthorsRepository;
import com.libraryproject.librarysystem.utilities.interfaces.IModelHelpers;
import com.libraryproject.librarysystem.utilities.interfaces.IUserHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorsControllers {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private IUserHelpers userHelpers;

    @Autowired
    private IAuthorsFactory authorsFactory;

    @Autowired
    private IModelHelpers modelHelpers;

    private final String accessAttributeName = "level";
    private final String authorAttributeName = "author";
    private final String authorsListRedirect = "redirect:/authorslist";

    @GetMapping("/authorslist")
    public String allAuthors(Model model) {
        List<Authors> authors = authorsRepository.findAll();
        Users user = userHelpers.getCurrentUser();
        modelHelpers.addUserToModel(model, user);
        model.addAttribute("authors", authors);
        return "authorlist.html";
    }

    @GetMapping("/addnewauthor")
    public String authorList(Model model) {
        Authors author = new Authors();
        model.addAttribute(authorAttributeName, author);
        return "addnewauthor.html";
    }

    @PostMapping("/addthisnewauthor")
    public String addAuthor(@RequestParam String authorName, String authorCountry) {
        Authors author = new Authors(authorName, authorCountry);
        authorsRepository.save(author);
        return authorsListRedirect;
    }

    @GetMapping("/viewauthor/{authorID}")
    public String infoOneAuthor(Model model, @PathVariable int authorID) {
        Authors author = authorsRepository.getById(authorID);
        Users user = userHelpers.getCurrentUser();
        modelHelpers.addUserToModel(model, user);
        model.addAttribute(authorAttributeName, author);
        return "infooneauthor.html";
    }

    @GetMapping("/viewauthor/edit/{id}")
    public String editAuthor(Model model, @PathVariable int id) {
        Authors authors = authorsRepository.getById(id);
        model.addAttribute(authorAttributeName, authors);
        return "editauthor.html";
    }

    @PostMapping("/editthisauthor")
    public String editThisAuthor(@Valid AuthorsDTO authorsDTO, Model model) {
        Authors author = authorsFactory.create(authorsDTO);
        authorsRepository.save(author);
        author = authorsRepository.getById(author.getAuthorID());
        model.addAttribute(authorAttributeName, author);
        return authorsListRedirect;
    }

    @GetMapping("/viewauthor/delete/{id}")
    public String deleteAuthor(@PathVariable int id, Model model) {
        authorsRepository.deleteById(id);
        List<Authors> authors = authorsRepository.findAll();
        model.addAttribute("authors", authorsRepository.findAll());
        return authorsListRedirect;
    }
}
