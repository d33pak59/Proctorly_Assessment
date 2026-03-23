package com.proctorly.assessment.repository;

import com.proctorly.assessment.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Long> {
}
