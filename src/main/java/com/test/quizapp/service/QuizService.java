package com.test.quizapp.service;

import com.test.quizapp.model.Question;
import com.test.quizapp.model.Quiz;
import com.test.quizapp.repository.QuestionRepository;
import com.test.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String title, int questions, String category) {

        try {
            List<Question> questionList = questionRepository.getRandomQuestionByCategory(category, questions);
            Quiz quiz = new Quiz();
            quiz.setQuizTitle(title);
            quiz.setQuestions(questionList);

            quizRepository.save(quiz);
            return new ResponseEntity<>("Successful", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return new ResponseEntity<>("Unsuccessful", HttpStatus.NOT_IMPLEMENTED);

    }
}
