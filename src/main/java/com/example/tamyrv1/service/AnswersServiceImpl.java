package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.AnswersDTO;
import com.example.tamyrv1.model.Answers;
import com.example.tamyrv1.repository.AnswersRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswersServiceImpl implements AnswersService {

    private final AnswersRepository answersRepository;

    public AnswersServiceImpl(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    @Override
    public AnswersDTO submitAnswer(AnswersDTO answersDTO) {
        // Создаём объект Answers без установки answerId
        Answers answer = new Answers();
        answer.setSurveyId(answersDTO.getSurveyId());
        answer.setUserId(answersDTO.getUserId());
        answer.setDate(Timestamp.valueOf(LocalDateTime.now()));
        answer.setAnswer(answersDTO.getAnswer());

        // Сохраняем и получаем сгенерированный answerId
        Answers savedAnswer = answersRepository.save(answer);

        // Возвращаем DTO
        return new AnswersDTO(
                savedAnswer.getAnswerId(),
                savedAnswer.getSurveyId(),
                savedAnswer.getUserId(),
                savedAnswer.getDate(),
                savedAnswer.getAnswer()
        );
    }

    @Override
    public List<AnswersDTO> getAnswersBySurveyId(Long surveyId) {
        return answersRepository.findBySurveyId(surveyId).stream()
                .map(a -> new AnswersDTO(
                        a.getAnswerId(),
                        a.getSurveyId(),
                        a.getUserId(),
                        a.getAnswer()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswersDTO> getAnswersByUserId(Long userId) {
        return answersRepository.findByUserId(userId).stream()
                .map(a -> new AnswersDTO(
                        a.getAnswerId(),
                        a.getSurveyId(),
                        a.getUserId(),
                        a.getAnswer()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasUserAnsweredSurvey(Long userId, Long surveyId) {
        return answersRepository.existsByUserIdAndSurveyId(userId, surveyId);
    }
}
