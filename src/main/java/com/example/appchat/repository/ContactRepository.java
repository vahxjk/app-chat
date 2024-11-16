package com.example.appchat.repository;

import com.example.appchat.dto.request.ContactRequest;
import com.example.appchat.dto.response.ContactResponse;
import com.example.appchat.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact findContactByEmail(String email);

    Contact findContactByPhone(String phone);

    List<Contact> findAllByUserId(Integer userId);
}
