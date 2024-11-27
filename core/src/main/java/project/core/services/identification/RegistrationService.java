package project.core.services.identification;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.dto.profile.UserDTO;
import project.core.entities.profile.PersonalData;
import project.core.entities.profile.User;
import project.core.enums.profile.UserRole;
import project.core.repositories.profile.UserRepository;

@Service
@Data
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String saveUser(UserDTO userDTO) {

        User user = User.builder()
                .login(userDTO.getLogin())
                .password(passwordEncoder.encode(userDTO.getPassword()))
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

        return "User saved successfully";
    }
}
