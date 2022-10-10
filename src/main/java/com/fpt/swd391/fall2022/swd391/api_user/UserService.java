package com.fpt.swd391.fall2022.swd391.api_user;

import com.fpt.swd391.fall2022.swd391.api_user.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseEntity<?> Login(UserDtoRequestLogin userDtoRequestLogin);

    ResponseEntity<?> Register(UserDtoRequest userDtoRequest);

    PageResponse<InformationUserDtoResponse> listFilterSearchPaging(String filter, int pageNo, int pageSize);


    ResponseEntity<?> UpdateInformation(InformationUserDtoRequest informationUserDtoRequest, Long id);

    ResponseEntity<?> Delete(Long id);
    ResponseEntity<?> ChangePassword(PasswordDtoRequest passwordDtoRequest, Long id);

    ResponseEntity<?> findById(Long id);



}
