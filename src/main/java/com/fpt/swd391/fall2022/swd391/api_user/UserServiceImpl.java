package com.fpt.swd391.fall2022.swd391.api_user;

import com.fpt.swd391.fall2022.swd391.api_user.dto.MessageResponse;
import com.fpt.swd391.fall2022.swd391.api_user.dto.UserDtoRequest;
import com.fpt.swd391.fall2022.swd391.api_user.dto.UserDtoRequestLogin;
import com.fpt.swd391.fall2022.swd391.api_user.dto.UserDtoResponse;
import com.fpt.swd391.fall2022.swd391.entity.Account;
import com.fpt.swd391.fall2022.swd391.entity.Role;
import com.fpt.swd391.fall2022.swd391.jwtToken.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final AuthenticationManager manager;
    final UserRoleRepository userRoleRepository;

    final JwtUtil jwtUtil;
    final PasswordEncoder passwordEncoder ;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager manager, UserRoleRepository userRoleRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.manager = manager;
        this.userRoleRepository = userRoleRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ResponseEntity<?> Register(UserDtoRequest userDtoRequest) {
        Optional<Account> optionalUser = userRepository.findByEmail(userDtoRequest.getEmail());
        if (optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is exist", userDtoRequest));
        }
        Account account = modelMapper.map(userDtoRequest,Account.class);
        account.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));

        Optional<Role> optionalRole = userRoleRepository.findById(6L);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            account.setRole(role);
            account.setStatus(true);
            userRepository.save(account);
            return ResponseEntity.ok().body(new MessageResponse("Create Email Successful", null));

        }
        return ResponseEntity.badRequest().body(new MessageResponse("Roles not found", userDtoRequest));

    }

    @Override
    public ResponseEntity<?> Login(UserDtoRequestLogin userDtoRequestLogin) {
        try {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDtoRequestLogin.getEmail(), userDtoRequestLogin.getPassword())
            );
            Account account = (Account) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(account);
            UserDtoResponse userDtoResponse = new UserDtoResponse(account.getEmail(), accessToken);
            return ResponseEntity.ok(userDtoResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email Failing", null));
        }


    }
}