package com.prabhav.LibraryManagement.Controller;

import com.prabhav.LibraryManagement.Entity.User;
import com.prabhav.LibraryManagement.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/AdminBMS")
    public String showAdminBMSPage() {
        return "AdminBMS";
    }

    @GetMapping("/list-students")
    public String showStudents(Model model) {
        List<User> students = userService.getAllStudents();
        model.addAttribute("students", students);
        System.out.println(students);
        return "list-students";
    }

}
