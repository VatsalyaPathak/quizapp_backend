package com.telusko.quizapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizapp.doa.QuestionDao;
import com.telusko.quizapp.doa.QuizDao;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    
    @Autowired
    QuestionDao questionDao;

    public Quiz createQuiz(String category, int numsQ, String title) {
        // Fetch random questions from the database
        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numsQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        //quizDao.save(quiz);
        return  quizDao.save(quiz);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        
        // Handle if the quiz is not found
        if (quiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        
        // Convert Question to QuestionWrapper
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizOpt = quizDao.findById(id);

        // Handle if the quiz is not found
        if (quizOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz quiz = quizOpt.get();
        List<Question> questions = quiz.getQuestions();

        // Sort the questions by their id
        questions.sort(Comparator.comparing(Question::getId));

        // Sort the responses by their id
        responses.sort(Comparator.comparing(Response::getId));

        int right = 0;

        // Iterate over the sorted user's responses and check against the correct answers
        for (int i = 0; i < responses.size(); i++) {
            Response userResponse = responses.get(i);
            Question question = questions.get(i); // Fetch the corresponding question

            System.out.println(question.getId() + "-" + userResponse.getId());

            if (userResponse.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
