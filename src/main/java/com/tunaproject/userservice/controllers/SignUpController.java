package com.tunaproject.userservice.controllers;

import com.tunaproject.userservice.DTO.SignUpRequest;
import com.tunaproject.userservice.domain.User;
import com.tunaproject.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/signup")
@CrossOrigin
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request){
        User user = new User();
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/")
                .path("{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
