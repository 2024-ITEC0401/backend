package com.itec0401.backend.domain.user.service;

import com.itec0401.backend.domain.user.dto.*;
import com.itec0401.backend.domain.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserService {
    ResponseEntity<UserInfoDto> getUserProfile(Authentication authentication);
    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);
    ResponseEntity<String> signIn(MemberDTO memberDTO);
    ResponseEntity<Boolean> isEmailEmpty(String email);
    Optional<User> checkPermission(Authentication authentication);
    ResponseEntity<Void> updateUserProfile(UpdateUserProfileDto updateUserProfileDto, Authentication authentication);
}