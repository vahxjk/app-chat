package com.example.appchat.controller;

import com.example.appchat.dto.request.ContactRequest;
import com.example.appchat.dto.response.ContactResponse;
import com.example.appchat.service.ContactService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContactController {

    ContactService contactService;

    @PostMapping
    ResponseEntity<ContactResponse> addContact(@RequestBody ContactRequest request){
        ContactResponse response = contactService.addContact(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
