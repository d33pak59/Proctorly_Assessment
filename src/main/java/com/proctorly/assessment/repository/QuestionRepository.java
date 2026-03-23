package com.proctorly.assessment.repository;

import com.proctorly.assessment.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
