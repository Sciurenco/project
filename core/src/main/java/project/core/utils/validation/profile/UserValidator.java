package project.core.utils.validation.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.core.dto.profile.UserDTO;
import project.core.enums.profile.UserRole;
import project.core.repositories.profile.UserRepository;

import java.util.List;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;

        if (userRepository.existsByLogin(user.getLogin())) {
            errors.rejectValue("login", "login.exists", "This login is already used");
        }

        if (user.getRole() != null && !List.of(UserRole.values()).contains(user.getRole())) {
            errors.rejectValue("role", "role.invalid", "This role is not valid");
        }

     }
}