package com.microservicesom.question_service.Repo;

import com.microservicesom.question_service.Dao.Question;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

    @Query(value = "SELECT id FROM Question q ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
    List<Integer> findQuestionsByLimit(Integer numberOfQuestions);


}
