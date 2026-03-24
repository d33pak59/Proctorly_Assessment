package com.proctorly.assessment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proctorly.assessment.dto.*;
import com.proctorly.assessment.entity.*;
import com.proctorly.assessment.enums.Difficulty;
import com.proctorly.assessment.enums.QuestionStatus;
import com.proctorly.assessment.enums.QuestionType;
import com.proctorly.assessment.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final ObjectMapper objectMapper;

    private int resolveCreditCost(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY   -> 1;
            case MEDIUM -> 2;
            case HARD   -> 3;
            case EXPERT -> 5;
        };
    }

    private List<QuestionTag> buildTags(List<String> tags, Question question) {
        if (tags == null || tags.isEmpty()) return List.of();
        return tags.stream().map(tag -> {
            QuestionTag qt = new QuestionTag();
            qt.setQuestion(question);
            qt.setTag(tag);
            return qt;
        }).toList();
    }

    private QuestionResponse toResponse(Question q) {
        QuestionResponse res = new QuestionResponse();
        res.setId(q.getId());
        res.setTenantId(q.getTenantId());
        res.setType(q.getType().name());
        res.setDifficulty(q.getDifficulty().name());
        res.setCreditCost(q.getCreditCost());
        res.setStatus(q.getStatus().name());
        res.setVersion(q.getVersion());
        res.setContentJson(q.getContentJson());
        res.setCreatedBy(q.getCreatedBy());
        res.setCreatedAt(q.getCreatedAt());
        res.setUpdatedAt(q.getUpdatedAt());
        res.setTags(
                q.getTags() != null
                        ? q.getTags().stream().map(QuestionTag::getTag).toList()
                        : List.of()
        );

        QuestionResponse.DomainInfo domainInfo = new QuestionResponse.DomainInfo();
        domainInfo.setId(q.getTopic().getDomain().getId());
        domainInfo.setName(q.getTopic().getDomain().getName());
        res.setDomain(domainInfo);

        QuestionResponse.TopicInfo topicInfo = new QuestionResponse.TopicInfo();
        topicInfo.setId(q.getTopic().getId());
        topicInfo.setName(q.getTopic().getName());
        topicInfo.setParentTopicName(
                q.getTopic().getParentTopic() != null
                        ? q.getTopic().getParentTopic().getName()
                        : null
        );
        res.setTopic(topicInfo);

        return res;
    }

    @Transactional
    public QuestionResponse createMcqQuestion(McqQuestionRequest req) {
        Topic topic = topicRepository.findById(Math.toIntExact(req.getTopicId()))
                .orElseThrow(() -> new RuntimeException("Topic not found: " + req.getTopicId()));

        Difficulty difficulty = Difficulty.valueOf(req.getDifficulty());

        Question question = new Question();
        question.setTenantId(req.getTenantId());
        question.setTopic(topic);
        question.setType(QuestionType.valueOf(req.getType()));
        question.setDifficulty(difficulty);
        question.setCreditCost(resolveCreditCost(difficulty));
        question.setContentJson(objectMapper.valueToTree(req.getContent()));
        question.setVersion(1);
        question.setStatus(QuestionStatus.DRAFT);
        question.setCreatedBy(req.getCreatedBy());
        question.setIsDeleted(false);
        question.setTags(buildTags(req.getTags(), question));

        return toResponse(questionRepository.save(question));
    }

    @Transactional
    public QuestionResponse createCodingQuestion(CodingQuestionRequest req) {
        Topic topic = topicRepository.findById(Math.toIntExact(req.getTopicId()))
                .orElseThrow(() -> new RuntimeException("Topic not found: " + req.getTopicId()));

        Difficulty difficulty = Difficulty.valueOf(req.getDifficulty());

        Question question = new Question();
        question.setTenantId(req.getTenantId());
        question.setTopic(topic);
        question.setType(QuestionType.CODING);
        question.setDifficulty(difficulty);
        question.setCreditCost(resolveCreditCost(difficulty));
        question.setContentJson(objectMapper.valueToTree(req.getContent()));
        question.setVersion(1);
        question.setStatus(QuestionStatus.DRAFT);
        question.setCreatedBy(req.getCreatedBy());
        question.setIsDeleted(false);
        question.setTags(buildTags(req.getTags(), question));

        return toResponse(questionRepository.save(question));
    }

    // once we clear that question should have unique Id then we move forward with this login.

//    public QuestionResponse getQuestion(UUID id) {
//        Question q = questionRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Question not found: " + id));
//        return toResponse(q);
//    }

    public List<QuestionResponse> getAllQuestions() {
        return questionRepository.findByIsDeletedFalse()
                .stream().map(this::toResponse).toList();
    }
}