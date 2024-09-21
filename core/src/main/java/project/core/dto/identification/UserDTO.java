package project.core.dto.identification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import project.core.enums.profile.UserRole;

@Data
public class UserDTO {

    @NotEmpty(message = "The login mustn't be empty")
    @Size(min = 3, max = 20, message = "The login must be between 3 and 20 characters.")
    private String login;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$", message = "The password must contain at least: " +
            "one lower case letter, one uppercase letter, one digit, one special character " +
            "and must be at least 8 characters long")
    private String password;
    private UserRole role;
    @Valid
    private PersonalDataDTO personalData;

}
