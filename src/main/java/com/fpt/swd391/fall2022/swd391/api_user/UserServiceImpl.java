package com.fpt.swd391.fall2022.swd391.api_user;

import com.fpt.swd391.fall2022.swd391.api_user.dto.*;
import com.fpt.swd391.fall2022.swd391.entity.Role;
import com.fpt.swd391.fall2022.swd391.exception.ResourceNotFoundException;
import com.fpt.swd391.fall2022.swd391.jwtToken.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final AuthenticationManager manager;
    final UserRoleRepository userRoleRepository;

    final JwtUtil jwtUtil;
    final PasswordEncoder passwordEncoder;

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
        Account account = modelMapper.map(userDtoRequest, Account.class);
        account.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));

        Optional<Role> optionalRole = userRoleRepository.findById(2L);
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
    public PageResponse<InformationUserDtoResponse> listFilterSearchPaging(String content, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        if (content == null) {
            content = "";
        }
        Page<Account> accountPage = userRepository.listFilterSearchPaging(content, pageable);
        if (accountPage.isEmpty()) {
            throw new ResourceNotFoundException("No Page");
        }

//        accountPage.forEach(account -> list.add(modelMapper.map(account, InformationUserDtoResponse.class)));

        return new PageResponse<InformationUserDtoResponse>()
                .setPageSize(accountPage.getSize())
                .setTotalSize(accountPage.getTotalElements())
                .setList(accountPage.getContent()
                        .stream()
                        .map(acc -> modelMapper.map(acc, InformationUserDtoResponse.class))
                        .collect(Collectors.toList()))
                .setPageNumber(accountPage.getNumber());


    }


    @Override
    public ResponseEntity<?> UpdateInformation(InformationUserDtoRequest informationUserDtoRequest, Long id) {
        Optional<Account> accountOptional = userRepository.findById(id);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.isStatus()) {
                account.setFullName(informationUserDtoRequest.getFullName());
                account.setPhone(informationUserDtoRequest.getPhone());
                account.setAddress(informationUserDtoRequest.getAddress());
                account.setImage(informationUserDtoRequest.getImage());
                return ResponseEntity.ok().body(new MessageResponse("Update Information successful", informationUserDtoRequest));
            }
            throw new ResourceNotFoundException("Account is blocked");

        }
        throw new ResourceNotFoundException("Account found");

    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        Optional<Account> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();
            if (account.isStatus()) {
                account.setStatus(false);
                userRepository.save(account);
                return ResponseEntity.ok().body(new MessageResponse("Delete Account successful", account.getEmail()));
            }
            throw new ResourceNotFoundException("Account found");

        }
        throw new ResourceNotFoundException("Account found");
    }

    @Override
    public ResponseEntity<?> ChangePassword(PasswordDtoRequest passwordDtoRequest, Long id) {
        Optional<Account> accountOptional = userRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (!passwordDtoRequest.getNewPassword().equals(passwordDtoRequest.getConfirmPassword())) {
                return ResponseEntity.badRequest().body(new MessageResponse("New password not same the face password verify", account.getEmail()));
            }
            account.setPassword(passwordEncoder.encode(passwordDtoRequest.getNewPassword()));
            userRepository.save(account);
            return ResponseEntity.ok().body(new MessageResponse("Change Password Successful", account.getEmail()));

        }
        throw new ResourceNotFoundException("Account found");
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Account> accountOptional = userRepository.findById(id);
        if (accountOptional.isPresent()) {
            if (accountOptional.get().isStatus()) {
                return ResponseEntity.ok().body(new MessageResponse("Search successful", modelMapper.map(accountOptional.get(), InformationUserDtoResponse.class)));
            }
            throw new ResourceNotFoundException("Account found");
        }
        throw new ResourceNotFoundException("Account found");
    }

    @Override
    public ResponseEntity<?> Login(UserDtoRequestLogin userDtoRequestLogin) {
        Optional<Account> accountOptional = userRepository.findByEmail(userDtoRequestLogin.getEmail());
        if(!(accountOptional.get()).isStatus()){
            return ResponseEntity.badRequest().body(new MessageResponse("Email Failing", null));
        }

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
