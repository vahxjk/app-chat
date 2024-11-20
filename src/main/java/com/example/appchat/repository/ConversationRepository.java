package com.example.appchat.repository;

import com.example.appchat.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    List<Conversation> findAllByCreatorId(Integer userId);

    @Query("SELECT c FROM Conversation c WHERE c.creator.id = :userId AND c.deletedDate is NULL ORDER BY c.updatedDate DESC ")
    Optional<Conversation> findActiveConversationByUserId(@Param("userId") Integer userId);
}
