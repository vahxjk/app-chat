package com.example.appchat.controller;

import com.example.appchat.dto.request.ConversationRequest;
import com.example.appchat.dto.response.ApiResponse;
import com.example.appchat.dto.response.ConversationResponse;
import com.example.appchat.dto.response.MessageResponse;
import com.example.appchat.service.ConversationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConversationController {

    ConversationService conversationService;

    @PostMapping
    ApiResponse<ConversationResponse> createConversation(@RequestBody ConversationRequest request){
        ConversationResponse response = conversationService.createConversation(request);

        return ApiResponse.<ConversationResponse>builder()
                .result(response)
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ConversationResponse> updateConversation(@PathVariable Integer id, @RequestBody ConversationRequest request){
        ConversationResponse response = conversationService.updateConversation(id, request);

        return ApiResponse.<ConversationResponse>builder()
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteConversation(@PathVariable Integer id){
        conversationService.deleteConversation(id);
        return ApiResponse.<String>builder()
                .result("Conversation has been deleted")
                .build();
    }

    @GetMapping
    ApiResponse<List<ConversationResponse>> getAllConversationsById(){
        return ApiResponse.<List<ConversationResponse>>builder()
                .result(conversationService.getAllConversationsById())
                .build();
    }
}
