package com.itec0401.backend.domain.coordinationclothing.service;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.coordination.entity.Coordination;
import com.itec0401.backend.domain.coordinationclothing.entity.CoordinationClothing;

import java.util.List;

public interface CoordinationClothingService {
    void createCoordinationClothing(Coordination coordination, Clothing clothing);
    void deleteCoordinationClothing(List<CoordinationClothing> coordinationClothingList);
}
