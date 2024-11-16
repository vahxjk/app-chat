package com.example.appchat.mapper;

import com.example.appchat.dto.request.ConversationRequest;
import com.example.appchat.dto.response.ConversationResponse;
import com.example.appchat.entity.Conversation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    Conversation toConversation(ConversationRequest request);

    ConversationResponse toConversationResponse(Conversation conversation);

    void updateConversation(@MappingTarget Conversation conversation, ConversationRequest request);

    List<ConversationResponse> toListConversationResponse(List<Conversation> conversationList);
}
