package com.fpt.swd391.fall2022.swd391.api_User;

import com.fpt.swd391.fall2022.swd391.api_User.dto.UserDtoRequest;
import com.fpt.swd391.fall2022.swd391.api_User.dto.UserDtoRequestLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> signIn(UserDtoRequestLogin userDtoRequestLogin);
    ResponseEntity<?> signUp(UserDtoRequest userDtoRequest);


}
