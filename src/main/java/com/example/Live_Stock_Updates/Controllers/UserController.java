package com.example.Live_Stock_Updates.Controllers;


import com.example.Live_Stock_Updates.DTOs.UserRegistrationRequest_DTO;
import com.example.Live_Stock_Updates.Models.User;
import com.example.Live_Stock_Updates.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
//
//    @PostMapping("/addUser")
//    public String

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest_DTO userRegistrationRequest_dto){
        User existing = userService.findByEmail(userRegistrationRequest_dto.getEmail());
        if (existing != null) {
            return "User Already Exists";
        }
        return userService.registerUser(userRegistrationRequest_dto);
    }

    @GetMapping("/login")
    public String loginRequest(@RequestParam String email,@RequestParam String password){

        return userService.loginUser(email,password);
    }



}
