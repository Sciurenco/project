package project.core.repositories.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import project.core.entities.profile.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {

}
