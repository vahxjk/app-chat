package com.example.appchat.controller;

import com.example.appchat.dto.request.UserRequest;
import com.example.appchat.dto.request.UserUpdateRequest;
import com.example.appchat.dto.response.UserResponse;
import com.example.appchat.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserController {

    UserService userService;

    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        UserResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<UserResponse>> getAllUsers(){
        try {
            List<UserResponse> allUsers = userService.getAllUsers();
            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Integer id){
        try {
            UserResponse response = userService.getUserById(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest userUpdateRequest){
        try {
            UserResponse response = userService.updateUser(id,userUpdateRequest);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Integer id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User has been deleted",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
