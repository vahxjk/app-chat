package com.example.appchat.controller;

import com.example.appchat.dto.request.MessageRequest;
import com.example.appchat.dto.response.ApiResponse;
import com.example.appchat.dto.response.MessageResponse;
import com.example.appchat.service.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MessageController {

    MessageService messageService;

    @PostMapping
    ApiResponse<MessageResponse> createMessage(@RequestBody MessageRequest messageRequest){
        MessageResponse response = messageService.createMessage(messageRequest);

        return ApiResponse.<MessageResponse>builder()
                .result(response)
                .build();
    }
}
