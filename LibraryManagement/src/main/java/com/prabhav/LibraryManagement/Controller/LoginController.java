package com.prabhav.LibraryManagement.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/403")
    public String forbidden(){
        return "403";
    }

    @GetMapping("/redirect")
    public String redirect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/list-book";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))
        || authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER")))
        {
            return "redirect:/list-book";
        } else {
            // handle other roles or scenarios as needed
            return "redirect:/";
        }
    }
}
