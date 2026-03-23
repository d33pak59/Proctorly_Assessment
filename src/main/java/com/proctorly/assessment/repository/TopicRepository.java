package com.proctorly.assessment.repository;

import com.proctorly.assessment.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
