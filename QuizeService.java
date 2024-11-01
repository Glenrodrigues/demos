package com.quiz.quizeService.service;

import java.util.List;

import com.quiz.quizeService.entities.quize;

public interface QuizeService {

    quize add(quize quiz);

    List<quize> get();

    quize getOne (Long id);
    
}
