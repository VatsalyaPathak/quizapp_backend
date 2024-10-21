package com.telusko.quizapp.doa;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telusko.quizapp.model.Quiz;



@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
	
	
	
	
}

