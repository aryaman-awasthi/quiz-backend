package com.test.quizapp.service;

import com.test.quizapp.model.Question;
import com.test.quizapp.model.Quiz;
import com.test.quizapp.model.QuizQuestions;
import com.test.quizapp.model.QuizResponse;
import com.test.quizapp.repository.QuestionRepository;
import com.test.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuizQuestions>> getQuiz(Integer id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            List<Question> questions = quiz.get().getQuestions();

            List<QuizQuestions> quizQuestions = new ArrayList<>();

            for (Question question : questions) {
                QuizQuestions q = new QuizQuestions(question.getId(), question.getQuestionTitle(), question.getOption1(),
                        question.getOption2(), question.getOption3(), question.getOption4());

                quizQuestions.add(q);
            }

            return new ResponseEntity<>(quizQuestions, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<Integer> getResult(Integer id, List<QuizResponse> quizResponses) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            List<Question> questions = quiz.get().getQuestions();

            int score = 0;

            HashMap <Integer, String> correctAnswers = new HashMap<>();

            for (Question question : questions) {
                int questionId = question.getId();
                String correctAnswer = question.getRightAnswer();

                correctAnswers.put(questionId, correctAnswer);
            }

            for (QuizResponse quizResponse : quizResponses) {
                if (quizResponse.getAnswer().equals(correctAnswers.get(quizResponse.getId()))) {
                    score++;
                }
            }

            return new ResponseEntity<>(score, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
