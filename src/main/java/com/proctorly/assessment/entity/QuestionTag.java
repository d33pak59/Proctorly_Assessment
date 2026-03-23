package com.proctorly.assessment.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "question_tags")
@IdClass(QuestionTag.QuestionTagId.class)
public class QuestionTag {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Id
    @Column(name = "tag", nullable = false)
    private String tag;

    @Data
    public static class QuestionTagId implements Serializable {
        private UUID question;
        private String tag;
    }
}
