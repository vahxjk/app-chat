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
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXISTS));


        Conversation conversation = conversationMapper.toConversation(request);
        conversation.setCreator(user);

        return conversationMapper.toConversationResponse(conversationRepository.save(conversation));
    }

    public ConversationResponse updateConversation(Integer id, ConversationRequest request) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        conversationMapper.updateConversation(conversation, request);

        return conversationMapper.toConversationResponse(conversationRepository.save(conversation));
    }

    public void deleteConversation(Integer id) {
        conversationRepository.deleteById(id);
    }

    public List<ConversationResponse> getAllConversationsById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication.getName());
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return conversationMapper.toListConversationResponse(conversationRepository.findAllByCreatorId(user.getId()));
    }
}
