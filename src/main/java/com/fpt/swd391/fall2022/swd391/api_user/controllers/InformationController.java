package com.fpt.swd391.fall2022.swd391.api_user.controllers;


import com.fpt.swd391.fall2022.swd391.api_user.UserService;
import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoRequest;
import com.fpt.swd391.fall2022.swd391.api_user.dto.InformationUserDtoResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.PasswordDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/Informations")
public class InformationController {
    final
    UserService userService;

    public InformationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{content}/{pageNo}/{pageSize}")
    List<InformationUserDtoResponse> listFilterSearchPaging(@PathVariable String content, @PathVariable int pageNo, @PathVariable int pageSize) {
        return userService.listFilterSearchPaging(content, pageNo, pageSize);
    }

    @GetMapping("/{pageNo}/{pageSize}")
    List<InformationUserDtoResponse> list(@PathVariable int pageNo, @PathVariable int pageSize) {
        return userService.list(pageNo, pageSize);
    }

    @PostMapping("/{id}")
    ResponseEntity<?> updateInformation(@RequestBody @Valid InformationUserDtoRequest request, @Valid @PathVariable Long id) {
        return userService.UpdateInformation(request, id);
    }

    @DeleteMapping("/accounts/{id}")
    ResponseEntity<?> deleteAccount(@Valid @PathVariable Long id) {
        return userService.Delete(id);
    }

    @PutMapping("/changepassword/{id}")
    ResponseEntity<?> changePassword(@RequestBody @Valid PasswordDtoRequest request, @Valid @PathVariable Long id) {
        return userService.ChangePassword(request, id);
    }
}
