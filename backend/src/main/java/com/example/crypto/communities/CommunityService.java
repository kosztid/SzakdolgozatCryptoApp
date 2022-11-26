package com.example.crypto.communities;

import com.example.crypto.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @GetMapping
    public List<Community> getCommunities() {
        return communityRepository.findAll();
    }

    @Transactional
    public void addCommunity(Community model) {
        communityRepository.save(model);
    }

    @Transactional
    public void sendMessage(String id, Message message) {
        Community community = communityRepository.findById(id).orElseThrow(() -> new IllegalStateException("no community"));
        if (community.getMembers().stream().anyMatch(m -> Objects.equals(m, message.getSenderemail()))) {
            System.out.println("already contains");
        } else {
            List<String> members = community.getMembers();
            members.add(message.getSenderemail());
            community.setMembers(members);
        }
        List<Message> messages = community.getMessages();
        messages.add(message);
        community.setMessages(messages);
    }
}
