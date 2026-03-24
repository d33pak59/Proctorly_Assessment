package com.proctorly.assessment.dto;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DomainResponse {
    private Long id;
    private UUID tenantId;
    private String name;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
}
