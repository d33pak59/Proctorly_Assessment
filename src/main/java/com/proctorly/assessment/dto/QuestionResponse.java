package com.proctorly.assessment.dto;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class QuestionResponse {
    private UUID id;
    private UUID tenantId;
    private Long topicId;
    private String type;
    private String difficulty;
    private Integer creditCost;
    private Object contentJson;
    private Integer version;
    private String status;
    private String createdBy;
    private LocalDateTime createdAt;
    private List<String> tags;
}