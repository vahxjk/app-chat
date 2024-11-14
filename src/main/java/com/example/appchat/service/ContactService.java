package com.example.appchat.service;

import com.example.appchat.dto.request.ContactRequest;
import com.example.appchat.dto.response.ContactResponse;
import com.example.appchat.entity.Contact;
import com.example.appchat.entity.User;
import com.example.appchat.exception.AppException;
import com.example.appchat.exception.ErrorCode;
import com.example.appchat.mapper.ContactMapper;
import com.example.appchat.repository.ContactRepository;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContactService {

    ContactRepository contactRepository;
    ContactMapper contactMapper;
    UserRepository userRepository;

    public ContactResponse addContact(ContactRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("auth: {}",authentication.getName()); // lay email nguoi dung
        String email = authentication.getName();
        if (contactRepository.findContactByPhone(request.getPhone()) != null) {
            throw new AppException(ErrorCode.PHONE_EXISTED);
        }
        if (contactRepository.findContactByEmail(request.getEmail()) != null) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Contact contact = contactMapper.toContact(request);
        contact.setUser(user);
        return contactMapper.toContactResponse(contactRepository.save(contact));
    }

    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }

    public ContactResponse updateContact(Integer id, ContactRequest contactRequest) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CONTACT_NOT_FOUND));
        contactMapper.updateContact(contact,contactRequest);
        return contactMapper.toContactResponse(contactRepository.save(contact));
    }

    public List<ContactResponse> getAllContactsById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication.getName());
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_EXISTS));
        Integer id = user.getId();
        log.info("id: {}", id);
        List<Contact> contactList = contactRepository.findAllByUserId(id);
        return contactMapper.toListContactResponse(contactList);
    }
}

