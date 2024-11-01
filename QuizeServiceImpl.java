package com.quiz.quizeService.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizeService.repository.quizRepo;

import com.quiz.quizeService.entities.quize;
import com.quiz.quizeService.service.QuestionClient;
import com.quiz.quizeService.service.QuizeService;

@Service
public class QuizeServiceImpl implements QuizeService {
    @Autowired
    private quizRepo quizRepo;
    
    @Autowired
    private QuestionClient questionClient;



    @Override
    public quize add(quize quiz) {
        
        return quizRepo.save(quiz);
    }

    @Override
    public List<quize> get() {
       

        List<quize> quizze=quizRepo.findAll();

        List<quize> newQuizze =quizze.stream().map(quize ->{
            quize.setQuestion(questionClient.getQuestionClientofQuiz(quize.getId()));
            return quize;
            }).collect(Collectors.toList());

        newQuizze.forEach(quiz -> {
                        System.out.println("Quiz ID: " + quiz.getId());
                        System.out.println("Questions: " + quiz.getQuestion());
                    });
           
        
        return newQuizze;
    }

    @Override
    public quize getOne(Long id) {
       quize quizee=quizRepo.findById(id).orElseThrow(()-> new RuntimeException("No id found"));
        quizee.setQuestion(questionClient.getQuestionClientofQuiz(quizee.getId()));
        return quizee;
    }
    
    
}
