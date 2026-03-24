package com.proctorly.assessment.dto;

import lombok.Data;

@Data
public class TopicRequest {
    private Long domainId;
    private Long parentTopicId; // null if root topic
    private String name;
    private String createdBy;
}
