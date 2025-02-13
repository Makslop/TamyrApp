package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.AnswersDTO;
import com.example.tamyrv1.model.Answers;
import com.example.tamyrv1.repository.AnswersRepository;
import org.springframework.stereotype.Service;

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
        Answers answer = new Answers(null, answersDTO.getSurveyId(), answersDTO.getUserId(), answersDTO.getDate(), answersDTO.getAnswer());
        answer = answersRepository.save(answer);
        return new AnswersDTO(answer.getAnswerId(), answer.getSurveyId(), answer.getUserId(), answer.getDate(), answer.getAnswer());
    }

    @Override
    public List<AnswersDTO> getAnswersBySurveyId(Long surveyId) {
        return answersRepository.findBySurveyId(surveyId).stream()
                .map(a -> new AnswersDTO(a.getAnswerId(), a.getSurveyId(), a.getUserId(), a.getDate(), a.getAnswer()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswersDTO> getAnswersByUserId(Long userId) {
        return answersRepository.findByUserId(userId).stream()
                .map(a -> new AnswersDTO(a.getAnswerId(), a.getSurveyId(), a.getUserId(), a.getDate(), a.getAnswer()))
                .collect(Collectors.toList());
    }
}
