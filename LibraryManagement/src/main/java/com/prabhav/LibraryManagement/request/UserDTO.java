package com.prabhav.LibraryManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "first name cannot be null")
    private String firstName;
    private String lastName;
    @Email(message = "Enter valid email Id")
    @NotNull(message = "email is required")

    private String email;
    private String phoneNumber;
    @NotBlank(message = "Username is required")
    @NotNull(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;

    private String role;

    public String checkProperties() throws IllegalAccessException {
        for(Field f : getClass().getDeclaredFields()){
            if(f.get(this) == null){
                String[] arr = f.toString().split("\\.");
                String t = arr[arr.length - 1];
                if(t.equalsIgnoreCase("username")
                    || t.equalsIgnoreCase("password")
                        || t.equalsIgnoreCase("role")
                        ){
                    return t;
                }
            }
        }
        return null;
    }

}
