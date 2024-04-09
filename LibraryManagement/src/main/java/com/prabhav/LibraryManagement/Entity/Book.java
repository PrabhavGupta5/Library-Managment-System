package com.prabhav.LibraryManagement.Entity;

import com.prabhav.LibraryManagement.Service.security.UserDetailsCustom;
import com.prabhav.LibraryManagement.request.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Boooks")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String description;
    private boolean borrowed;


    @ManyToOne
    public void setBorrowedBy(UserDetailsCustom user) {
    }

}
