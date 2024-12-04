package com.itec0401.backend.domain.clothing.entity;

import com.itec0401.backend.domain.clothing.dto.ClothRequestDto;
import com.itec0401.backend.domain.clothing.entity.type.*;
import com.itec0401.backend.domain.coordinationclothing.entity.CoordinationClothing;
import com.itec0401.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Clothing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothing_id")
    private Long id;

    private String imageUri;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category mainCategory;

    @Enumerated(EnumType.STRING)
    private Category subCategory;

    @Enumerated(EnumType.STRING)
    private ColorType baseColor;

    @Enumerated(EnumType.STRING)
    private ColorType pointColor;

    @Enumerated(EnumType.STRING)
    private TextileType textile;

    @Enumerated(EnumType.STRING)
    private PatternType pattern;

    @Enumerated(EnumType.STRING)
    private SeasonType season;

    @Enumerated(EnumType.STRING)
    private StyleType style;

    private String description;

    @OneToMany(mappedBy = "clothing")
    private List<CoordinationClothing> coordinationClothingList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void update(ClothRequestDto dto){
        this.imageUri = dto.getImageUri();
        this.name = dto.getName();
        this.mainCategory = Category.convertString(dto.getMainCategory());
        this.subCategory = Category.convertString(dto.getSubCategory());
        this.baseColor = ColorType.convertString(dto.getBaseColor());
        this.pointColor = ColorType.convertString(dto.getPointColor());
        this.textile = TextileType.convertString(dto.getTextile());
        this.pattern = PatternType.convertString(dto.getPattern());
        this.season = SeasonType.convertString(dto.getSeason());
        this.style = StyleType.convertString(dto.getStyle());
        this.description = dto.getDescription();
    }

    @Builder
    public Clothing(String imageUri,
                    String name,
                    String mainCategory,
                    String subCategory,
                    String baseColor,
                    String pointColor,
                    String textile,
                    String pattern,
                    String season,
                    String style,
                    String description,
                    User user) {
        this.imageUri = imageUri;
        this.name = name;
        this.mainCategory = Category.convertString(mainCategory);
        this.subCategory = Category.convertString(subCategory);
        this.baseColor = ColorType.convertString(baseColor);
        this.pointColor = ColorType.convertString(pointColor);
        this.textile = TextileType.convertString(textile);
        this.pattern = PatternType.convertString(pattern);
        this.season = SeasonType.convertString(season);
        this.style = StyleType.convertString(style);
        this.description = description;
        this.user = user;
    }

}
