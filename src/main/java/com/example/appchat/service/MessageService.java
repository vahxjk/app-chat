package com.example.appchat.service;

import com.example.appchat.dto.request.MessageRequest;
import com.example.appchat.dto.response.MessageResponse;
import com.example.appchat.entity.Conversation;
import com.example.appchat.entity.Message;
import com.example.appchat.entity.User;
import com.example.appchat.exception.AppException;
import com.example.appchat.exception.ErrorCode;
import com.example.appchat.mapper.MessageMapper;
import com.example.appchat.repository.ConversationRepository;
import com.example.appchat.repository.MessageRepository;
import com.example.appchat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class MessageService {

    MessageRepository messageRepository;
    MessageMapper messageMapper;
    UserRepository userRepository;
    ConversationRepository conversationRepository;


    public MessageResponse createMessage(MessageRequest messageRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer senderId = Integer.valueOf(authentication.getName());
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Conversation conversation = conversationRepository.findActiveConversationByUserId(senderId)
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        Message message = messageMapper.toMessage(messageRequest);
        message.setSender(sender);
        message.setConversation(conversation);
        var response = messageMapper.toMessageResponse(messageRepository.save(message));
        response.setSenderId(sender.getId());
        response.setConversationId(conversation.getId());
        return response;
    }

    public MessageResponse updateMessage(Integer id) {
        return null;
    }
}
