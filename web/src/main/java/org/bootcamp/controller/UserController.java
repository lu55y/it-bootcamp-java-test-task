package org.bootcamp.controller;

import jakarta.validation.Valid;
import org.bootcamp.UserService;
import org.bootcamp.dto.UserRegistrationRequest;
import org.bootcamp.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRegistrationRequest userRequest){
        return userService.createUser(userRequest) ? new ResponseEntity<>("User created.", HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        try {
            Pageable paging = PageRequest.of(page, size, Sort.by("email"));

            Page<UserResponse> userResponsePage = userService.getAllUsers(paging);

            List<UserResponse> users = userResponsePage.getContent();

            Map<String,Object> response = new HashMap<>();
            response.put("currentPage", userResponsePage.getNumber());
            response.put("totalItems", userResponsePage.getTotalElements());
            response.put("totalPages", userResponsePage.getTotalPages());
            response.put("users", users);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
