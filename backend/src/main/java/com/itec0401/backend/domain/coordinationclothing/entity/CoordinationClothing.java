package com.itec0401.backend.domain.coordinationclothing.entity;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.coordination.entity.Coordination;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CoordinationClothing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clothing_id")
    private Clothing clothing;

    @ManyToOne
    @JoinColumn(name = "coordination_id")
    private Coordination coordination;

    @Builder
    public CoordinationClothing(Clothing clothing, Coordination coordination) {
        this.clothing = clothing;
        this.coordination = coordination;
    }
}
