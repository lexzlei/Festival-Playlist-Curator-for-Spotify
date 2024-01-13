package com.lexlei.festivalplaylistapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lexlei.festivalplaylistapp.Models.User;
import com.lexlei.festivalplaylistapp.Repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public @ResponseBody String addUser(@RequestParam String firstName,
    String lastName, String email, String password) {
        
        User n = new User();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setEmail(email);
        n.setPassword(password);
        userRepository.save(n);
        return "Saved";

    }
    
}
