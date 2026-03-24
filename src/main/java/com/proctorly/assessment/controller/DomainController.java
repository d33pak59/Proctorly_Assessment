package com.proctorly.assessment.controller;

import com.proctorly.assessment.dto.DomainRequest;
import com.proctorly.assessment.dto.DomainResponse;
import com.proctorly.assessment.service.DomainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assessment/domain")
@AllArgsConstructor
public class DomainController {
    public final DomainService domainService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DomainResponse create(@RequestBody DomainRequest req) {
        return domainService.create(req);
    }

    @GetMapping
    public List<DomainResponse> getAll(@RequestParam UUID tenantId) {
        return domainService.getAll(tenantId);
    }
}
