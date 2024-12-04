package com.itec0401.backend.domain.coordination.entity;

import com.itec0401.backend.domain.coordinationclothing.entity.CoordinationClothing;
import com.itec0401.backend.domain.user.entity.User;
import com.itec0401.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Coordination extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordination_id")
    private Long id;

    private String name;

    private String description;
    // hashtags 공백 두고 String concat 해야할 듯, hashtag 테이블로 떼기엔 조금 과함.
    private String hashtags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "coordination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoordinationClothing> coordinationClothingList;

    @Builder
    public Coordination(String name, String description, List<String> hashtags, User user) {
        this.name = name;
        this.description = description;
        this.hashtags = String.join(",", hashtags);
        this.user = user;
    }
}
