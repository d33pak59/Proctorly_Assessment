package com.proctorly.assessment.controller;

import com.proctorly.assessment.dto.*;
import com.proctorly.assessment.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assessment/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/mcq")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResponse createMcq(@RequestBody McqQuestionRequest req) {
        return questionService.createMcqQuestion(req);
    }

    @PostMapping("/coding")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResponse createCoding(@RequestBody CodingQuestionRequest req) {
        return questionService.createCodingQuestion(req);
    }

//    @GetMapping("/{id}")
//    public QuestionResponse getById(@PathVariable UUID id) {
//        return questionService.getQuestion(id);
//    }

    @GetMapping
    public List<QuestionResponse> getAll() {
        return questionService.getAllQuestions();
    }
}