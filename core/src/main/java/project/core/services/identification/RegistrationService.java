package project.core.services.identification;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.dto.identification.UserDTO;
import project.core.entities.identification.PersonalData;
import project.core.entities.identification.User;
import project.core.enums.profile.UserRole;
import project.core.repositories.profile.UserRepository;

import java.util.Base64;

@Service
@Data
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUser(UserDTO userDTO) {

        User user = User.builder()
                .login(userDTO.getLogin())
                .password(encodePassword(userDTO.getPassword()))
                .role(UserRole.USER)
                .personalData(PersonalData.builder()
                        .name(userDTO.getPersonalData().getName())
                        .surname(userDTO.getPersonalData().getSurname())
                        .gender(userDTO.getPersonalData().getGender())
                        .dateOfBirth(userDTO.getPersonalData().getDateOfBirth())
                        .email(userDTO.getPersonalData().getEmail())
                        .phoneNumber(userDTO.getPersonalData().getPhoneNumber())
                        .build())
                .build();

        user.getPersonalData().setUser(user);

        userRepository.save(user);

    }

    public String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
