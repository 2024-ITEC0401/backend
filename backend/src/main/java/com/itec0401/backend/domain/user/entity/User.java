package com.itec0401.backend.domain.user.entity;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.coordination.entity.Coordination;
import com.itec0401.backend.domain.user.dto.UpdateUserProfileDto;
import com.itec0401.backend.domain.user.dto.UserRequestDto;
import com.itec0401.backend.domain.usercolor.entity.UserColor;
import com.itec0401.backend.domain.userstyle.entity.UserStyle;
import com.itec0401.backend.global.BaseEntityWithUpdatedAt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntityWithUpdatedAt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // google login API 보고 email, password 필드 생각 다시 해야함
    // google 로그인 하면 이메일, 닉네임 받아올 수 있으려나?

    // 유저 추가 정보
    private int gender;
    private int age;
    private double height;
    private double weight;
    private String tone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Clothing> clothingList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Coordination> coordinationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserStyle> userStyleList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserColor> userColorList;

    public static User toEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.getName())
                .nickname(userRequestDto.getNickname())
                .email(userRequestDto.getEmail())
                .gender(userRequestDto.getGender())
                .age(userRequestDto.getAge())
                .height(userRequestDto.getHeight())
                .weight(userRequestDto.getWeight())
                .tone(userRequestDto.getTone())
                .build();
    }

    public void update(UpdateUserProfileDto updateUserProfileDto) {
        this.nickname = updateUserProfileDto.getNickname();
        this.age = updateUserProfileDto.getAge();
        this.gender = updateUserProfileDto.getGender();
        this.height = updateUserProfileDto.getHeight();
        this.weight = updateUserProfileDto.getWeight();
        this.tone = updateUserProfileDto.getTone();
    }

    @Builder
    public User(String name, String nickname, String email, int gender, int age, double height, double weight, String tone) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.tone = tone;
    }
}



