package org.bootcamp.impl;

import org.bootcamp.UserService;
import org.bootcamp.dto.UserRegistrationRequest;
import org.bootcamp.dto.UserResponse;
import org.bootcamp.model.User;
import org.bootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUser(UserRegistrationRequest userRequest) {
        User user = userRegistrationRequestToUser(userRequest);
        return userRepository.save(user).getId() != null;
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable paging) {
        Page<User> userPage = userRepository.findAll(paging);
        return userPage.map(this::userToUserResponse);
    }

    private UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .fullName(getFullUserName(user))
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    private String getFullUserName(User user) {
        return user.getLastname() + " " + user.getFirstname() + " " + user.getSurname();
    }

    private User userRegistrationRequestToUser(UserRegistrationRequest userRequest) {
        User user = new User();
        user.setLastname(userRequest.getLastname());
        user.setFirstname(userRequest.getFirstname());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());

        return user;
    }


}
