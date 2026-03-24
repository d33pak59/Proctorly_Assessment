package com.proctorly.assessment.controller;

import com.proctorly.assessment.dto.TopicRequest;
import com.proctorly.assessment.dto.TopicResponse;
import com.proctorly.assessment.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assessment/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicResponse create(@RequestBody TopicRequest req) {
        return topicService.create(req);
    }

    @GetMapping
    public List<TopicResponse> getByDomain(@RequestParam Long domainId) {
        return topicService.getByDomain(domainId);
    }
}