package com.proctorly.assessment.service;
import com.proctorly.assessment.dto.DomainRequest;
import com.proctorly.assessment.dto.DomainResponse;
import com.proctorly.assessment.entity.Domain;
import com.proctorly.assessment.repository.DomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainService {

    private final DomainRepository domainRepository;

    public DomainResponse create(DomainRequest req) {
        Domain domain = new Domain();
        domain.setTenantId(req.getTenantId());
        domain.setName(req.getName());
        domain.setDescription(req.getDescription());
        domain.setCreatedBy(req.getCreatedBy());
        return toResponse(domainRepository.save(domain));
    }

    public List<DomainResponse> getAll(UUID tenantId) {
        return domainRepository.findByTenantId(tenantId)
                .stream().map(this::toResponse).toList();
    }

    private DomainResponse toResponse(Domain d) {
        DomainResponse res = new DomainResponse();
        res.setId(d.getId());
        res.setTenantId(d.getTenantId());
        res.setName(d.getName());
        res.setDescription(d.getDescription());
        res.setCreatedBy(d.getCreatedBy());
        res.setCreatedAt(d.getCreatedAt());
        return res;
    }
}