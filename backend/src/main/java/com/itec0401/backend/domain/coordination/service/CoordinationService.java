package com.itec0401.backend.domain.coordination.service;

import com.itec0401.backend.domain.coordination.dto.BasicCodiRequestToSpring;
import com.itec0401.backend.domain.coordination.dto.NLCodiRequestToSpring;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface CoordinationService {
    ResponseEntity<Void> coordRecommendBasic(BasicCodiRequestToSpring dto, Authentication authentication);
    ResponseEntity<Void> coordRecommendUsingNaturalLanguage(NLCodiRequestToSpring dto, Authentication authentication);
}
