package com.test.quizapp.controller;

import com.test.quizapp.model.QuizQuestions;
import com.test.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam int questions, @RequestParam String category) {
        return quizService.createQuiz(title, questions, category);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuizQuestions>> getQuiz (@PathVariable Integer id) {
        return quizService.getQuiz(id);
    }
}
