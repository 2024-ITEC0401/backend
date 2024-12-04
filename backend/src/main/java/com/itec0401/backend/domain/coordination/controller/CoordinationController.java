package com.itec0401.backend.domain.coordination.controller;

import com.itec0401.backend.domain.coordination.dto.BasicCodiRequestToSpring;
import com.itec0401.backend.domain.coordination.dto.CodiDetails;
import com.itec0401.backend.domain.coordination.dto.CodiInfo;
import com.itec0401.backend.domain.coordination.dto.NLCodiRequestToSpring;
import com.itec0401.backend.domain.coordination.service.CoordinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coordination")
public class CoordinationController {
    private final CoordinationService coordinationService;

    @PostMapping("/rec/basic")
    public ResponseEntity<Void> coordRecommendBasic(@RequestBody BasicCodiRequestToSpring dto, Authentication authentication){
        return coordinationService.coordRecommendBasic(dto, authentication);
    }

    @PostMapping("/rec/natural-language")
    public ResponseEntity<Void> coordRecommendUsingNaturalLanguage(@RequestBody NLCodiRequestToSpring dto, Authentication authentication){
        return coordinationService.coordRecommendUsingNaturalLanguage(dto, authentication);
    }

    @GetMapping
    public ResponseEntity<List<CodiInfo>> getAllCoordinationInfos(Authentication authentication){
        return coordinationService.getAllCodiInfos(authentication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteCoordinationInfo(@PathVariable("id") Long id, Authentication authentication){
        return coordinationService.deleteCodiById(id, authentication);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodiDetails> getCoordinationDetails(@PathVariable("id") Long id, Authentication authentication){
        return coordinationService.getCoordinationDetails(id, authentication);
    }

}
