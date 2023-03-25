package com.example.Live_Stock_Updates.DTOs;


import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest_DTO {


    @NonNull
    private String first_name;
    @NonNull
    private String last_name;
    @NonNull
    private String email;
    @NonNull
    private String phone_number;
    @NonNull
    private String password;
}
