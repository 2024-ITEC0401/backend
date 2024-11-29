package com.itec0401.backend.domain.clothing.repository;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {
    @Query(value = "select * from clothing c where :id = c.clothing_id", nativeQuery = true)
    Optional<Clothing> findByClothingId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from clothing c where :user_id = c.user_id and :clothing_id = c.clothing_id", nativeQuery = true)
    Integer deleteByTwoId(@Param("user_id") Long user_id, @Param("clothing_id") Long clothing_id);
}
