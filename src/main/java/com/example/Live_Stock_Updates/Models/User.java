package com.example.Live_Stock_Updates.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    first_name : string
//    - last_name : string
//    - email : string
//    - phone_number : string
//    - password : string

    @MongoId(FieldType.OBJECT_ID)
    private int id;

    private String first_name;

    private String last_name;

    private String email;

    private String phone_number;

    private String password;

    @DBRef
    private List<Role> roles;


}
