package com.itec0401.backend.domain.coordinationclothing.repository;

import com.itec0401.backend.domain.coordinationclothing.entity.CoordinationClothing;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoordinationClothingRepository extends JpaRepository<CoordinationClothing, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from coordination_clothing cc where :id = cc.id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
