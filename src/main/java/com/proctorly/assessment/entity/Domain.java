package com.proctorly.assessment.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "domains")
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt ;

    @OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
    private List<Topic> topics;
}