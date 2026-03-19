package com.test.quizapp.service;

import com.test.quizapp.model.Question;
import com.test.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }


    public String addQuestion(Question question) {
        questionRepository.save(question);
        return "success";
    }

    public String deleteQuestion(int id) {
        questionRepository.deleteById(id);
        return "success";
    }

    public Question updateQuestion(int id, Question question) {
        Question existing = questionRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setCategory(question.getCategory());
            existing.setDifficultylevel(question.getDifficultylevel());
            existing.setQuestionTitle(question.getQuestionTitle());
            existing.setOption1(question.getOption1());
            existing.setOption2(question.getOption1());
            existing.setOption3(question.getOption3());
            existing.setOption4(question.getOption4());
            existing.setRightAnswer(question.getRightAnswer());
            return questionRepository.save(existing);
        }
        return null;
    }
}
