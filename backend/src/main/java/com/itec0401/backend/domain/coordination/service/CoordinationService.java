package com.itec0401.backend.domain.coordination.service;

import com.itec0401.backend.domain.coordination.dto.BasicCodiRequestToSpring;
import com.itec0401.backend.domain.coordination.dto.CodiDetails;
import com.itec0401.backend.domain.coordination.dto.CodiInfoWithImages;
import com.itec0401.backend.domain.coordination.dto.NLCodiRequestToSpring;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CoordinationService {
    ResponseEntity<Void> coordRecommendBasic(BasicCodiRequestToSpring dto, Authentication authentication);
    ResponseEntity<CodiInfoWithImages> coordRecommendUsingNaturalLanguage(NLCodiRequestToSpring dto, Authentication authentication);
    ResponseEntity<List<CodiInfoWithImages>> getAllCodiInfoWithImages(Authentication authentication);
    ResponseEntity<Integer> deleteCodiById(Long id, Authentication authentication);
    ResponseEntity<CodiDetails> getCoordinationDetails(Long id, Authentication authentication);
}