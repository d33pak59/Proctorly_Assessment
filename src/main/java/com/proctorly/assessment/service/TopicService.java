package com.proctorly.assessment.service;

import com.proctorly.assessment.dto.TopicRequest;
import com.proctorly.assessment.dto.TopicResponse;
import com.proctorly.assessment.entity.Domain;
import com.proctorly.assessment.entity.Topic;
import com.proctorly.assessment.repository.DomainRepository;
import com.proctorly.assessment.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final DomainRepository domainRepository;

    public TopicResponse create(TopicRequest req) {
        Domain domain = domainRepository.findById(req.getDomainId())
                .orElseThrow(() -> new RuntimeException("Domain not found: " + req.getDomainId()));

        Topic topic = new Topic();
        topic.setDomain(domain);
        topic.setName(req.getName());
        topic.setCreatedBy(req.getCreatedBy());

        if (req.getParentTopicId() != null) {
            Topic parent = topicRepository.findById(Math.toIntExact(req.getParentTopicId()))
                    .orElseThrow(() -> new RuntimeException("Parent topic not found: " + req.getParentTopicId()));
            topic.setParentTopic(parent);
        }

        return toResponse(topicRepository.save(topic));
    }

    public List<TopicResponse> getByDomain(Long domainId) {
        return topicRepository.findByDomainId(domainId)
                .stream().map(this::toResponse).toList();
    }

    private TopicResponse toResponse(Topic t) {
        TopicResponse res = new TopicResponse();
        res.setId(t.getId());
        res.setName(t.getName());
        res.setCreatedBy(t.getCreatedBy());
        res.setCreatedAt(t.getCreatedAt());

        TopicResponse.DomainInfo domainInfo = new TopicResponse.DomainInfo();
        domainInfo.setId(t.getDomain().getId());
        domainInfo.setName(t.getDomain().getName());
        res.setDomain(domainInfo);

        if (t.getParentTopic() != null) {
            TopicResponse.ParentTopicInfo parentInfo = new TopicResponse.ParentTopicInfo();
            parentInfo.setId(t.getParentTopic().getId());
            parentInfo.setName(t.getParentTopic().getName());
            res.setParentTopic(parentInfo);
        }

        return res;
    }
}