package com.example.Live_Stock_Updates.Services;


import com.example.Live_Stock_Updates.DTOs.UserRegistrationRequest_DTO;
import com.example.Live_Stock_Updates.Models.Role;
import com.example.Live_Stock_Updates.Models.User;
import com.example.Live_Stock_Updates.Repositories.RoleRepository;
import com.example.Live_Stock_Updates.Repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    private int identity=0;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public String registerUser(UserRegistrationRequest_DTO userRegistrationRequest_dto){

        User user = new User();
        user.setFirst_name(userRegistrationRequest_dto.getFirst_name());
        user.setLast_name(userRegistrationRequest_dto.getLast_name());
        user.setEmail(userRegistrationRequest_dto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest_dto.getPassword()));
        System.out.println(passwordEncoder.encode(userRegistrationRequest_dto.getPassword()));
        user.setPhone_number(userRegistrationRequest_dto.getPhone_number());

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }

        identity++;
        user.setId(identity);
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

        return "You have Successfully registered with Live_Stocks_Updates";

    }


    private @NotNull Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String  loginUser(String email,String password) {
        String msg = "";
        User user = userRepository.findByEmail(email);
        if (user != null) {
           // String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user1 = userRepository.findOneByEmailAndPassword(email, encodedPassword);
                if (user1.isPresent()) {
                    return"Login Success";
                } else {
                    return"Login Failed";
                }
            } else {

                return "password Not Match";
            }
        }
        return "Email not exits";




    }
}
