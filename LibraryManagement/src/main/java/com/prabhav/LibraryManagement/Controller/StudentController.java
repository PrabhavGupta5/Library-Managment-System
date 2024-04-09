package com.prabhav.LibraryManagement.Controller;

import com.prabhav.LibraryManagement.Entity.User;
import com.prabhav.LibraryManagement.Service.BookServiceImpl;
import com.prabhav.LibraryManagement.Service.UserServiceImpl;
import com.prabhav.LibraryManagement.request.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class StudentController {
    @Autowired
    private UserServiceImpl strservice;

    @Autowired
    private BookServiceImpl bookService;


    @GetMapping("/StudentBMS")
    public String showStudentBMSPage() {
        return "StudentBMS";
    }

    @GetMapping("/student-bookManage")
    public String showStudentbookManagePage() {
        return "student-bookManage";
    }

    @GetMapping("/addStudent")
    public String showAddStudentForm(Model model) {
        model.addAttribute( "user",new UserDTO());
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String addStudent(@Valid UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(user);
            return "addStudent";
        }
        try {
            user.setRole("USER");
            System.out.println(user);
            strservice.registerAccount(user);
            return "redirect:/list-students";
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return "error"; // Redirect to an error page or handle the error as needed
        }
    }


}
