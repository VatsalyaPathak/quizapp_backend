package com.telusko.quizapp.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telusko.quizapp.model.Question;




@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	
	List<Question>findByCategory(String category);
	
	@Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numsQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category, int numsQ);
}
