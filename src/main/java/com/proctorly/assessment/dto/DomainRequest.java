package com.proctorly.assessment.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DomainRequest {
    private UUID tenantId;
    private String name;
    private String description;
    private String createdBy;
}
