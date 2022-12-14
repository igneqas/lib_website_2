package com.libraryproject.librarysystem.controllers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.domain.DTO.UsersDTO;
import com.libraryproject.librarysystem.domain.factories.interfaces.IUsersFactory;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import com.libraryproject.librarysystem.security.MyUserDetails;
import com.libraryproject.librarysystem.utilities.interfaces.IUserHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.List;

@Controller
public class UsersControllers {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private IUsersFactory usersFactory;

    @Autowired
    private IUserHelpers userHelpers;

    @GetMapping("/signup")
    public String userSignup(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);

        return "signup.html";
    }

    @PostMapping("/signupnewuser")
    public String addUser(@Valid UsersDTO usersDTO, RedirectAttributes redirectAttributes) {
        Users user = usersFactory.create(usersDTO);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAccessLevel(AccessLevel.CLIENT);
        redirectAttributes.addFlashAttribute("message", "Succesfully signed up");
        usersRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/viewuserprofile")
    public String viewUserProfile(Model model) {
        Users user = userHelpers.getCurrentUser();
        model.addAttribute("user", user);
        return "infooneuser.html";
    }

    @GetMapping("/viewuserprofile/edit")
    public String editProfile(Model model) {
        Users user = userHelpers.getCurrentUser();
        model.addAttribute("user", user);
        return "edituserprofile.html";
    }

    @PostMapping("/editthisuserprofile")
    public String editThisUserProfile(@Valid UsersDTO usersDTO, Model model) {
        Users user = usersFactory.create(usersDTO);
        usersRepository.save(user);
        user = usersRepository.getById(user.getUserID());
        model.addAttribute("user", user);
        return "redirect:/viewuserprofile";
    }

    @GetMapping("/userslist")
    public String allUsers(Model model) {
        List<Users> users = usersRepository.findAll();

        model.addAttribute("users", users);
        return "authorlist.html";
    }

    @GetMapping("/viewuser/{id}")
    public String viewOneUser(Model model, @PathVariable int id) {
        Users user = usersRepository.getById(id);
        model.addAttribute("user", user);
        return "infooneuser.html";
    }

    @GetMapping("/viewuser/edit/{id}")
    public String editUser(Model model,@PathVariable int id) {
        Users user = usersRepository.getById(id);
        model.addAttribute("user", user);
        return "edituser.html";
    }

    @PostMapping("/editthisuser")
    public String editThisUser(@Valid UsersDTO usersDTO, Model model) {
        Users user = usersFactory.create(usersDTO);
        usersRepository.save(user);
        user = usersRepository.getById(user.getUserID());
        model.addAttribute("user", user);
        return "redirect:/userslist";
    }

    @GetMapping("/viewuser/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        System.out.println("Trying to delete this user: " + id );
        usersRepository.deleteById(id);
        return "redirect:/userslist";
    }
}
