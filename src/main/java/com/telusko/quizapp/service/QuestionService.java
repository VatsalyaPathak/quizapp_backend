package com.telusko.quizapp.service;

import com.telusko.quizapp.doa.QuestionDao;
import com.telusko.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;
	
	public List<Question>getAllQuestion() {
		return questionDao.findAll();		
	}
	
	public List<Question>getQuestionByCategory(String category) {
		return questionDao.findByCategory(category);
	}
	
	public Question addQuestion(Question question) {
		 return questionDao.save(question);
		
	}

}