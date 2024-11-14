package com.example.appchat.controller;

import com.example.appchat.dto.request.ContactRequest;
import com.example.appchat.dto.response.ApiResponse;
import com.example.appchat.dto.response.ContactResponse;
import com.example.appchat.service.ContactService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContactController {

    ContactService contactService;

    @PostMapping
    ApiResponse<ContactResponse> addContact(@RequestBody ContactRequest request){
        ContactResponse response = contactService.addContact(request);
        return ApiResponse.<ContactResponse>builder()
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteContact(@PathVariable Integer id){
        contactService.deleteContact(id);
        return ApiResponse.<String>builder()
                .result("Contact has been deleted")
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ContactResponse> updateContact(@PathVariable Integer id, @RequestBody ContactRequest contactRequest){
        ContactResponse response = contactService.updateContact(id,contactRequest);
        return ApiResponse.<ContactResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping
    ApiResponse<List<ContactResponse>> getAllContactsById(){
        List<ContactResponse> contactResponse = contactService.getAllContactsById();
        return ApiResponse.<List<ContactResponse>>builder()
                .result(contactResponse)
                .build();
    }
}
