package org.bootcamp;

import org.bootcamp.dto.UserRegistrationRequest;
import org.bootcamp.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    boolean createUser(UserRegistrationRequest userRequest);

    Page<UserResponse> getAllUsers(Pageable paging);
}
