package com.example.appchat.service;

import com.example.appchat.dto.request.ConversationRequest;
import com.example.appchat.dto.response.ConversationResponse;
import com.example.appchat.entity.Conversation;
import com.example.appchat.entity.User;
import com.example.appchat.exception.AppException;
import com.example.appchat.exception.ErrorCode;
import com.example.appchat.mapper.ConversationMapper;
import com.example.appchat.repository.ConversationRepository;
import com.example.appchat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ConversationService {

    ConversationRepository conversationRepository;
    ConversationMapper conversationMapper;
    UserRepository userRepository;

    public ConversationResponse createConversation(ConversationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer id = Integer.valueOf(authentication.getName());

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));


        Conversation conversation = conversationMapper.toConversation(request);
        conversation.setCreator(user);

        var response = conversationMapper.toConversationResponse(conversationRepository.save(conversation));
        response.setCreatorId(user.getId());
        return response;
    }

    public ConversationResponse updateConversation(Integer id, ConversationRequest request) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        conversationMapper.updateConversation(conversation, request);
        var response = conversationMapper.toConversationResponse(conversationRepository.save(conversation));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = Integer.valueOf(authentication.getName());
        response.setCreatorId(userId);
        return response;
    }

    public void deleteConversation(Integer id) {
        conversationRepository.deleteById(id);
    }

    public List<ConversationResponse> getAllConversationsById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication.getName());
        Integer id = Integer.valueOf(authentication.getName());
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return conversationMapper.toListConversationResponse(conversationRepository.findAllByCreatorId(user.getId()));
    }
}
