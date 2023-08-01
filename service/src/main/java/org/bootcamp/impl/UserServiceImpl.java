package org.bootcamp.test_task_for_java_developer.services.impl;

import org.bootcamp.test_task_for_java_developer.dtos.UserRequest;
import org.bootcamp.test_task_for_java_developer.dtos.UserResponse;
import org.bootcamp.test_task_for_java_developer.models.User;
import org.bootcamp.test_task_for_java_developer.repositories.UserRepository;
import org.bootcamp.test_task_for_java_developer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUser(UserRequest userRequest) {
        User user = userRequestToUser(userRequest);
        return userRepository.save(user).getId() != null;
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable paging) {
        Page<User> userPage = userRepository.findAll(paging);
        return userPage.map(this::userToUserResponse);
    }

    private UserResponse userToUserResponse(User user) {
        return new UserResponse(
                user.getLastname(),
                user.getFirstname(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );
    }

    private User userRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setLastname(userRequest.getLastname());
        user.setFirstname(userRequest.getFirstname());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());

        return user;
    }


}
