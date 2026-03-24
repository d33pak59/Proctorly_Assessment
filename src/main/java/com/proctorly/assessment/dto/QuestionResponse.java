package com.proctorly.assessment.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class QuestionResponse {
    private UUID id;
    private UUID tenantId;
    private String type;
    private String difficulty;
    private Integer creditCost;
    private String status;
    private Integer version;
    private Object contentJson;
    private List<String> tags;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DomainInfo domain;
    private TopicInfo topic;

    @Data
    public static class DomainInfo {
        private Long id;
        private String name;
    }

    @Data
    public static class TopicInfo {
        private Long id;
        private String name;
        private String parentTopicName;
    }
}