package com.microservicesom.question_service.Service;

import com.microservicesom.question_service.Dao.Question;
import com.microservicesom.question_service.Dao.QuestionWrapper;
import com.microservicesom.question_service.Dao.Response;
import com.microservicesom.question_service.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    QuestionRepo questionRepo;

    //adding questions to database
    public ResponseEntity<String> addQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(1, "What is Java?", "Programming language", "Database", "Framework", "Operating System", "Programming language", "Easy"));
        questions.add(new Question(2, "Which company developed Java?", "Microsoft", "Apple", "Google", "Sun Microsystems", "Sun Microsystems", "Medium"));
        questions.add(new Question(3, "Which is a valid keyword in Java?", "volatile", "include", "main", "exit", "volatile", "Medium"));
        questions.add(new Question(4, "Which is not a primitive data type in Java?", "int", "float", "boolean", "String", "String", "Easy"));
        questions.add(new Question(5, "Which method is the entry point of a Java program?", "start()", "run()", "main()", "init()", "main()", "Easy"));
        questions.add(new Question(6, "Which of these access specifiers is the most restrictive?", "public", "protected", "private", "default", "private", "Medium"));
        questions.add(new Question(7, "Which exception is thrown when dividing by zero in Java?", "IOException", "ArithmeticException", "ArrayIndexOutOfBoundsException", "NullPointerException", "ArithmeticException", "Hard"));
        questions.add(new Question(8, "Which package contains the String class in Java?", "java.util", "java.io", "java.lang", "java.sql", "java.lang", "Medium"));
        questions.add(new Question(9, "Which keyword is used to inherit a class in Java?", "extends", "implements", "inherits", "super", "extends", "Medium"));
        questions.add(new Question(10, "Which operator is used for concatenating two strings in Java?", "+", "-", "*", "/", "+", "Easy"));

        questionRepo.saveAll(questions);
        return new ResponseEntity<>(questions.size() + " questions added successfully", HttpStatus.CREATED);
    }


    //get questions form database

    public ResponseEntity<List<Question>> getQuestions() {

        List<Question> qs = questionRepo.findAll();

        if (qs.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(qs, HttpStatus.OK);
    }


    //delete questions by id in databse
    public ResponseEntity<String> deleQuestion(int id) {

        if (questionRepo.existsById(id)) {

            questionRepo.deleteById(id);
            return new ResponseEntity<>("question with id " + id + " deleted successfully", HttpStatus.OK);

        } else
            return new ResponseEntity<>(id + " not found", HttpStatus.NOT_FOUND);
    }

    //deleting all questions
    public ResponseEntity<String> deleteAllQuestions() {
        questionRepo.deleteAll();
        return new ResponseEntity<>("all Questions Delete Successfully", HttpStatus.OK);
    }


    public ResponseEntity<List<Integer>> generateQuetionsForQuiz(Integer numberOfQuestions) {
        List<Integer> questions = questionRepo.findQuestionsByLimit(numberOfQuestions);


        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> questions = new ArrayList<>();
        List<Question> QuestioinfromDb = new ArrayList<>();

        for (Integer q : questionIds) {
            QuestioinfromDb.add(questionRepo.findById(q).get());
        }

        for (Question ques : QuestioinfromDb) {
            questions.add(new QuestionWrapper(ques.getId(), ques.getQuetionTitle(), ques.getOption1(), ques.getOption2(), ques.getOption3(), ques.getOption4()));
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);

    }

    public ResponseEntity<Integer> submitRespones(List<Response> responses) {
        Integer right = 0;

        for (Response res : responses)
        {

            Question question=questionRepo.findById(res.getId()).get();

            if (res.getResponse().equals(question.getCorrectAnswer()))
            {
                right++;
            }


        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}