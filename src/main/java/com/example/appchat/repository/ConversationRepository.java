package com.example.appchat.repository;

import com.example.appchat.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    List<Conversation> findAllByCreatorId(Integer userId);
}
