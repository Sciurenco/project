package project.core.repositories.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.core.entities.profile.User;
import project.core.enums.profile.UserRole;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    @Modifying
    @Query("UPDATE User u SET u.role = :role WHERE u.id = :id")
    void setRole(Long id, UserRole role);
}
