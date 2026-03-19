package com.test.quizapp.service;

import com.test.quizapp.model.Question;
import com.test.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepository.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>("cannot add question", HttpStatus.NOT_MODIFIED);

    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionRepository.deleteById(id);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>("cannot add question", HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<Question> updateQuestion(int id, Question question) {
        Question existing = questionRepository.findById(id).orElse(null);
        try {
            if (existing != null) {
                existing.setCategory(question.getCategory());
                existing.setDifficultylevel(question.getDifficultylevel());
                existing.setQuestionTitle(question.getQuestionTitle());
                existing.setOption1(question.getOption1());
                existing.setOption2(question.getOption1());
                existing.setOption3(question.getOption3());
                existing.setOption4(question.getOption4());
                existing.setRightAnswer(question.getRightAnswer());
                return new ResponseEntity<>(questionRepository.save(existing), HttpStatus.OK);
            }
        } catch (Exception e) {
             System.out.println(e.toString());
        }
        return new ResponseEntity<>(new Question(), HttpStatus.NO_CONTENT);
    }
}
