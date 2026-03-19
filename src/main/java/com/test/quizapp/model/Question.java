package com.test.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    private String difficultylevel;

    @Column(name = "question_title", length = 1000)
    private String questionTitle;

    @Column(length = 1000)
    private String option1;

    @Column(length = 1000)
    private String option2;

    @Column(length = 1000)
    private String option3;

    @Column(length = 1000)
    private String option4;

    @Column(name = "right_answer", length = 1000)
    private String rightAnswer;
}