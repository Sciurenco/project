package project.core.services.profile;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.dto.profile.PersonalDataDTO;
import project.core.entities.profile.PersonalData;
import project.core.repositories.profile.PersonalDataRepository;
import project.core.utils.bean.BeanUtilsNonNull;

@Service
public class PersonalDataService {

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Transactional(readOnly = true)
    public PersonalData getPersonalData(long userId) {
       return personalDataRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Transactional
    public String updatePersonalData(PersonalDataDTO personalDataDTO, long id) {

        PersonalData updatedPersonalData = getPersonalData(id);
        BeanUtilsNonNull.copyNonNullProperties(personalDataDTO, updatedPersonalData);
        personalDataRepository.save(updatedPersonalData);

        return "Personal data updated successfully";
    }

    @Transactional
    public String deletePersonalData(long id) {
        personalDataRepository.deleteById(id);
        return "User deleted successfully";
    }

}
