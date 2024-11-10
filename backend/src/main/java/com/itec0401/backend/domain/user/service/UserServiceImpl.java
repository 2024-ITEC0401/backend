package com.itec0401.backend.domain.user.service;

import com.itec0401.backend.domain.color.entity.Color;
import com.itec0401.backend.domain.color.service.ColorService;
import com.itec0401.backend.domain.style.entity.Style;
import com.itec0401.backend.domain.style.service.StyleService;
import com.itec0401.backend.domain.user.dto.*;
import com.itec0401.backend.domain.user.entity.User;
import com.itec0401.backend.domain.user.jwt.JwtTokenProvider;
import com.itec0401.backend.domain.user.repository.UserRepository;
import com.itec0401.backend.domain.usercolor.service.UserColorService;
import com.itec0401.backend.domain.userstyle.service.UserStyleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserColorService userColorService;
    private final UserStyleService userStyleService;
    private final StyleService styleService;
    private final ColorService colorService;

    @Override
    public ResponseEntity<UserInfoDto> getUserProfile(Authentication authentication){
        Optional<User> user = checkPermission(authentication);
        if (user.isEmpty()){
            return new ResponseEntity<>(UserInfoDto.builder().build(), HttpStatus.BAD_REQUEST);
        }
        User validUser = user.get();
        return new ResponseEntity<>(UserInfoDto.toDto(validUser), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        //AccessToken 만들어서 줘야함
        Optional<User> byEmail = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (byEmail.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!passwordEncoder.matches(loginRequestDTO.getPassword(), byEmail.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().
                    accessToken(jwtTokenProvider.createAccessToken(loginRequestDTO.getEmail()))
                    .build();
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> signIn(MemberDTO memberDTO) {
        try {
            // 비밀번호 인코딩
            String encoded = passwordEncoder.encode(memberDTO.getPassword());

            // 회원 저장
            User saved = userRepository.save(User.builder()
                    .name(memberDTO.getName())
                    .email(memberDTO.getEmail())
                    .nickname(memberDTO.getNickName())
                    .password(encoded)
                    .build());

            // 저장된 객체가 null인 경우 처리
            return new ResponseEntity<>("회원가입 완료", HttpStatus.OK); // 성공 시 200 반환
        } catch (Exception e) {
            // 예외 발생 시 처리
            log.info("회원가입 중 오류: " + e);
            return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST); // 이메일 조회 후 회원가입 할것
        }
    }

    @Override
    public ResponseEntity<Boolean> isEmailEmpty(String email) {
        return new ResponseEntity<>(userRepository.findByEmail(email).isEmpty(), HttpStatus.OK);
    }

    @Override
    public Optional<User> checkPermission(Authentication authentication){
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public ResponseEntity<UserInfoDto> updateUserProfile(UpdateUserProfileDto updateUserProfileDto, Authentication authentication){
        // Authentication으로 이메일 파싱
        // 이메일로 유저 불러오기 (없는 경우는 없겠지만 혹시나 모르니 분기)
        Optional<User> user = checkPermission(authentication);
        if (user.isEmpty()){
            return new ResponseEntity<>(UserInfoDto.builder().build(), HttpStatus.BAD_REQUEST);
        }
        User validUser = user.get();

        // 컬러, 스타일 매핑 정보 모두 삭제
        userColorService.deleteAllUserColors(validUser);
        userStyleService.deleteAllUserStyles(validUser);

        // 새롭게 컬러, 스타일 매핑
        for (String color : updateUserProfileDto.getColorList()){
            Optional<Color> cur = colorService.findColorByName(color);
            cur.ifPresent(value -> userColorService.createUserColor(validUser, value));
        }
        for (String style : updateUserProfileDto.getStyleList()) {
            Optional<Style> cur = styleService.findStyleByName(style);
            cur.ifPresent(value -> userStyleService.createUserStyle(validUser, value));
        }
        validUser.update(updateUserProfileDto);
        userRepository.save(validUser);
        return new ResponseEntity<>(UserInfoDto.toDto(validUser), HttpStatus.OK);
    }
}
