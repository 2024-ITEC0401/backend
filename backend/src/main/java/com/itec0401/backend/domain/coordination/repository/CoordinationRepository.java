package com.itec0401.backend.domain.coordination.repository;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.coordination.entity.Coordination;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CoordinationRepository extends JpaRepository<Coordination, Long> {
    @Query(value = "select * from coordination c where :id = c.coordination_id", nativeQuery = true)
    Optional<Coordination> findByCoordinationId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from coordination c where :user_id = c.user_id and :coordination_id = c.coordination_id", nativeQuery = true)
    Integer deleteByTwoId(@Param("user_id") Long user_id, @Param("coordination_id") Long coordination_id);
}
