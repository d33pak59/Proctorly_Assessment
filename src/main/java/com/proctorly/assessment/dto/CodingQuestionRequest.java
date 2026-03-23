package com.proctorly.assessment.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class CodingQuestionRequest {
    private UUID tenantId;
    private Long topicId;
    private String difficulty;
    private String createdBy;
    private List<String> tags;
    private CodingContent content;

    @Data
    public static class CodingContent {
        private String question;
        private String problemStatement;
        private String constraints;
        private String sampleInput;
        private String sampleOutput;
        private Map<String, String> starterCode; // key = language, value = code
        private List<String> supportedLanguages;
        private List<TestCase> testCases;
        private String explanation;
        private Integer timeLimitSec;
    }

    @Data
    public static class TestCase {
        private String input;
        private String output;
        private Boolean isHidden;
    }
}
