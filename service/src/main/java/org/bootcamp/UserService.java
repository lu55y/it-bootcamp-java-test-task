package org.bootcamp.test_task_for_java_developer.services;

import org.bootcamp.test_task_for_java_developer.dtos.UserRequest;
import org.bootcamp.test_task_for_java_developer.dtos.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    boolean createUser(UserRequest userRequest);

    Page<UserResponse> getAllUsers(Pageable paging);
}
