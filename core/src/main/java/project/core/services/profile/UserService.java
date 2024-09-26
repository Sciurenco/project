package project.core.services.profile;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.dto.profile.UserDTO;
import project.core.entities.profile.User;
import project.core.repositories.profile.UserRepository;
import project.core.utils.bean.BeanUtilsNonNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Transactional
    public String updateUser(UserDTO user, long id) {
        User updatedUser = getUser(id);
        BeanUtilsNonNull.copyNonNullProperties(user, updatedUser);
        userRepository.save(updatedUser);

        return "User updated successfully";
    }

    @Transactional
    public String deleteUser(long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
