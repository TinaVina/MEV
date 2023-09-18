package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Clan;
import com.example.demo.models.User;
import com.example.demo.service.ClanService;
import com.example.demo.service.UserService;
import org.springframework.ui.Model;


@Controller
public class LoginController {

	@Autowired
    private ClanService clanService;

    @GetMapping("/prijava")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new User());
        return "login";
    }

    @PostMapping("/prijava")
    public String login(@ModelAttribute("loginForm") Clan clan, Model model) {
        // Check if the email and password fields are empty
        if (clan.getEmail().isEmpty() && clan.getPassword().isEmpty()) {
            model.addAttribute("emailError", "Ispunite ovo polje");
            model.addAttribute("passwordError", "Ispunite ovo polje");
            return "login";
        } else if (clan.getEmail().isEmpty()) { // Check if only the email field is empty
            model.addAttribute("emailError", "Ispunite ovo polje");
            model.addAttribute("email", ""); // Clear the email value if it was entered
            return "login";
        } else if (clan.getPassword().isEmpty()) {
            model.addAttribute("passwordError", "Ispunite ovo polje");
            if (!clan.getEmail().isEmpty()) {
                model.addAttribute("email", clan.getEmail()); // Remember the entered email
            }
            return "login";
        }

        // check if user exists in the database
        Clan foundClan = clanService.findClanByEmailAndPassword(clan.getEmail(), clan.getPassword());
        if (foundClan == null) {
            // user not found, add error message to model and return login page
            model.addAttribute("errorMessage", "Invalid email or password");
            model.addAttribute("email", clan.getEmail()); // Maintain the entered email value
            return "login";
        } else {
            // user found, redirect to home page
            return "redirect:/pocetna"; // or return "redirect:/home.html";
        }
    }



    
    


}



