package com.microservicesom.question_service.Dao;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id ;
    private String quetionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
    private  String difficultyLevel;

    public Question() {
    }

    public Question(Integer id, String quetionTitle, String option1, String option2, String option3, String option4, String correctAnswer, String difficultyLevel) {
        this.id = id;
        this.quetionTitle = quetionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
    }
}
