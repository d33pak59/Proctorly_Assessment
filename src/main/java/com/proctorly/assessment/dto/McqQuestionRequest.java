package com.proctorly.assessment.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class McqQuestionRequest {
    private UUID tenantId;
    private Long topicId;
    private String type;        // MCQ_SINGLE or MCQ_MULTI
    private String difficulty;  // EASY, MEDIUM, HARD, EXPERT
    private String createdBy;
    private List<String> tags;
    private McqContent content;

    @Data
    public static class McqContent {
        private String question;
        private List<Option> options;
        private List<String> answerKey;
        private String explanation;
        private Integer timeLimitSec;
    }

    @Data
    public static class Option {
        private String label;   // A, B, C, D
        private String text;
    }
}
