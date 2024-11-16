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
        Conversation conversation = conversationRepository.findById(messageRequest.getConversationId())
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        User sender = userRepository.findById(messageRequest.getSenderId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        messageRequest.setConversationId(conversation.getId());
        messageRequest.setSenderId(sender.getId());
        Message message = messageMapper.toMessage(messageRequest);

        return messageMapper.toMessageResponse(messageRepository.save(message));
    }
}
