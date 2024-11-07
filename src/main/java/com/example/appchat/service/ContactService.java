package com.example.appchat.service;

import com.example.appchat.dto.request.ContactRequest;
import com.example.appchat.dto.response.ContactResponse;
import com.example.appchat.entity.Contact;
import com.example.appchat.mapper.ContactMapper;
import com.example.appchat.repository.ContactRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContactService {

    ContactRepository contactRepository;
    ContactMapper contactMapper;

    public ContactResponse addContact(ContactRequest request) {
        Contact contactByEmail = contactRepository.findContactByEmail(request.getEmail());
        Contact contactByPhone = contactRepository.findContactByPhone(request.getPhone());
        if (contactByPhone != null) throw new RuntimeException("Phone is already exists");
        if(contactByEmail != null) throw new RuntimeException("Email is already exists");

        Contact contact = contactMapper.toContact(request);


        return contactMapper.toContactResponse(contactRepository.save(contact));
    }
}
