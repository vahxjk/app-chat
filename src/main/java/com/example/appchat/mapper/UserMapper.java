package com.example.appchat.mapper;

import com.example.appchat.dto.request.UserRequest;
import com.example.appchat.dto.request.UserUpdateRequest;
import com.example.appchat.dto.response.UserResponse;
import com.example.appchat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toListUserResponse(List<User> userList);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
