package com.proctorly.assessment.entity;

import com.fasterxml.jackson.databind.JsonNode;

import com.proctorly.assessment.entity.ENUM.Difficulty;
import com.proctorly.assessment.entity.ENUM.QuestionStatus;
import com.proctorly.assessment.entity.ENUM.QuestionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "question_type_enum", nullable = false)
    private QuestionType type;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "difficulty_enum", nullable = false)
    private Difficulty difficulty;

    @Column(name = "credit_cost", nullable = false)
    private Integer creditCost;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content_json", columnDefinition = "jsonb", nullable = false)
    private JsonNode contentJson;

    @Column(nullable = false)
    private Integer version = 1;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "question_status_enum", nullable = false)
    private QuestionStatus status = QuestionStatus.DRAFT;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionTag> tags;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionVersion> versions;
}
