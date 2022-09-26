package com.fpt.swd391.fall2022.swd391.api_User;

import com.fpt.swd391.fall2022.swd391.api_User.dto.UserDtoRequest;
import com.fpt.swd391.fall2022.swd391.api_User.dto.UserDtoRequestLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody @Valid UserDtoRequestLogin request) {
        return userService.signIn(request);
    }
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody @Valid UserDtoRequest request) {
        return userService.signUp(request);
    }
}
//test