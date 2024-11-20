package com.example.appchat.mapper;

import com.example.appchat.dto.request.ContactRequest;
import com.example.appchat.dto.response.ContactResponse;
import com.example.appchat.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact toContact(ContactRequest request);

    ContactResponse toContactResponse(Contact contact);

    void updateContact(@MappingTarget Contact contact, ContactRequest request);

    List<ContactResponse> toListContactResponse(List<Contact> contactList);
}
