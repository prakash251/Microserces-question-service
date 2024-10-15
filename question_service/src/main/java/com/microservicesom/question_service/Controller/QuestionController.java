package com.microservicesom.question_service.Controller;

import com.microservicesom.question_service.Dao.Question;
import com.microservicesom.question_service.Dao.QuestionWrapper;
import com.microservicesom.question_service.Dao.Response;
import com.microservicesom.question_service.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("getq")
    public ResponseEntity<List<Question>> getquestions() {
        return questionService.getQuestions();
    }


    @PostMapping("addq")
    public ResponseEntity<String> addQuestions() {
        return questionService.addQuestions();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestionByid(@PathVariable int id) {
        return questionService.deleQuestion(id);
    }

    @DeleteMapping("deleteall")
    public ResponseEntity<String> deleteAllQuestions() {
        return questionService.deleteAllQuestions();
    }


    //generate questions
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam  Integer numberOfQuestions)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.generateQuetionsForQuiz(numberOfQuestions);
    }
//getQuestion by question id

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }
//submit

    @PostMapping("getscore")
    public ResponseEntity<Integer> submitResponse(@RequestBody List<Response> responses){
        return questionService.submitRespones(responses);
    }

}