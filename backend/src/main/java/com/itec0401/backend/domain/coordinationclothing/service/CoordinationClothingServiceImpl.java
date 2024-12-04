package com.itec0401.backend.domain.coordinationclothing.service;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.coordination.entity.Coordination;
import com.itec0401.backend.domain.coordinationclothing.entity.CoordinationClothing;
import com.itec0401.backend.domain.coordinationclothing.repository.CoordinationClothingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordinationClothingServiceImpl implements CoordinationClothingService {
    private final CoordinationClothingRepository coordinationClothingRepository;

    @Override
    @Transactional
    public void createCoordinationClothing(Coordination coordination, Clothing clothing) {
        CoordinationClothing coordinationClothing = CoordinationClothing.builder().clothing(clothing).coordination(coordination).build();
        coordinationClothingRepository.save(coordinationClothing);
        // Coordination 과 Clothing 에도 중간테이블 연결 정보 추가
//        coordination.getCoordinationClothingList().add(coordinationClothing);
//        clothing.getCoordinationClothingList().add(coordinationClothing);
    }

    @Override
    @Transactional
    public void deleteCoordinationClothing(List<CoordinationClothing> coordinationClothingList) {
        for (CoordinationClothing coordinationClothing : coordinationClothingList) {
            coordinationClothingRepository.deleteById(coordinationClothing.getId());
        }
    }
}
