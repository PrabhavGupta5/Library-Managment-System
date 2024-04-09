package com.prabhav.LibraryManagement.Controller;

import com.prabhav.LibraryManagement.Service.UserService;
import com.prabhav.LibraryManagement.request.UserDTO;
import com.prabhav.LibraryManagement.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register-account")
    public ResponseEntity<BaseResponse> registerAccount(@RequestBody UserDTO userDTO) {
        System.out.println(">>>>>> "+userDTO.getUsername());
        return ResponseEntity.ok(userService.registerAccount(userDTO));
    }

}
