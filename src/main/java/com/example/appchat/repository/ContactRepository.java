package com.example.appchat.repository;

import com.example.appchat.dto.request.ContactRequest;
import com.example.appchat.dto.response.ContactResponse;
import com.example.appchat.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact findContactByEmail(String email);

    Contact findContactByPhone(String phone);
}
