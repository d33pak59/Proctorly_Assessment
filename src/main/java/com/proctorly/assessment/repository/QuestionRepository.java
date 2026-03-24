package com.proctorly.assessment.repository;

import com.proctorly.assessment.entity.Question;
import com.proctorly.assessment.enums.Difficulty;
import com.proctorly.assessment.enums.QuestionStatus;
import com.proctorly.assessment.enums.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.lang.ScopedValue;
import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByIsDeletedFalse();
    List<Question> findByTypeAndIsDeletedFalse(QuestionType type);
    List<Question> findByDifficultyAndIsDeletedFalse(Difficulty difficulty);
    List<Question> findByStatusAndIsDeletedFalse(QuestionStatus status);

   // <T> ScopedValue<T> findById(UUID id);
}
