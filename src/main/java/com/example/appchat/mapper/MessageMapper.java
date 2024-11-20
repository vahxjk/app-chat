package com.example.appchat.mapper;

import com.example.appchat.dto.request.MessageRequest;
import com.example.appchat.dto.response.MessageResponse;
import com.example.appchat.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message toMessage(MessageRequest request);

    MessageResponse toMessageResponse(Message message);

    void updateMessage(@MappingTarget Message message, MessageRequest request);

    List<MessageResponse> toListMessageResponse(List<Message> messageList);
}
