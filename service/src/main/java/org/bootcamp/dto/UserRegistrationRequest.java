package org.bootcamp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bootcamp.model.enums.Roles;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @NotBlank(message = "The field must not be empty")
    @Size(min = 1, max = 40, message = "Wrong format. Length must match 1-40 characters")
    @Pattern(regexp = "^[a-zA-Z -']*$", message = "Only latin letters are allowed")
    private String lastname;

    @NotBlank(message = "The field must not be empty")
    @Size(min = 1, max = 20, message = "Wrong format. Length must match 1-20 characters")
    @Pattern(regexp = "^[a-zA-Z -']*$", message = "Only latin letters are allowed")
    private String firstname;

    @NotBlank(message = "The field must not be empty")
    @Size(min = 1, max = 40, message = "Wrong format. Length must match 1-40 characters")
    @Pattern(regexp = "^[a-zA-Z -']*$", message = "Only latin letters are allowed")
    private String surname;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Must match email format")
    @Size(max = 50, message = "Wrong format. Max length 50 characters")
    private String email;

    @NotBlank(message = "Invalid role.")
    @Enumerated(EnumType.STRING)
    private Roles role;
}
