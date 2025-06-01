package com.example.tamyrv1.repository;

import com.example.tamyrv1.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
    List<Answers> findBySurveyId(Long surveyId);
    List<Answers> findByUserId(Long userId);
    boolean existsByUserIdAndSurveyId(Long userId, Long surveyId);
}