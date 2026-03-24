package com.proctorly.assessment.repository;

import com.proctorly.assessment.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    List<Domain> findByTenantId(UUID tenantId);
}
