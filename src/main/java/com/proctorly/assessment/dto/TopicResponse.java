package com.proctorly.assessment.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TopicResponse {
    private Long id;
    private String name;
    private String createdBy;
    private LocalDateTime createdAt;
    private DomainInfo domain;
    private ParentTopicInfo parentTopic;

    @Data
    public static class DomainInfo {
        private Long id;
        private String name;
    }

    @Data
    public static class ParentTopicInfo {
        private Long id;
        private String name;
    }
}
