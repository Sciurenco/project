package project.core.services.admin;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.enums.profile.UserRole;
import project.core.exceptions.admin.RoleException;
import project.core.repositories.profile.UserRepository;

import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    private UserRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    public String setRole(JsonNode jsonNode) {
        Long id = jsonNode.get("id").asLong();
        String role = jsonNode.get("role").asText();

        try {
            repository.setRole(id, UserRole.valueOf(role));
        } catch (IllegalArgumentException e) {
            throw new RoleException("Invalid role: " + role + ", only next values are valuable: " + List.of(UserRole.values()));
        }

        return "Role updated successfully";
    }
}
