package com.telusko.quizapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController { 
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/")
	public List<Question> getAllQuestion(){
		System.out.println("Anand");
		return questionService.getAllQuestion();
	}
	
	@GetMapping	("/category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category) {
		return questionService.getQuestionByCategory(category);
	}
	
	
	@PostMapping("/add")
	public Question addQuestion(@RequestBody Question question) {
		return  questionService.addQuestion(question);
	}
	
	
}