package com.itec0401.backend.domain.clothing.repository;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {
}
