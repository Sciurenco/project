package project.core.controllers.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.core.dto.profile.PersonalDataDTO;
import project.core.entities.profile.PersonalData;
import project.core.exceptions.profile.PersonalDataException;
import project.core.services.profile.PersonalDataService;
import project.core.utils.validation.profile.PersonalDataValidator;

@RestController
@RequestMapping("/personal/data")
public class PersonalDataController {

    @Autowired
    private PersonalDataValidator personalDataValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personalDataValidator);
    }

    @Autowired
    private PersonalDataService personalDataService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PersonalData> getPersonalData(@PathVariable("id") long userId) {
       return ResponseEntity.ok(personalDataService.getPersonalData(userId));
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<String> updatePersonalData(@PathVariable("id") long id,
                                                     @Validated @RequestBody PersonalDataDTO personalData, BindingResult result) {

        personalDataValidator.validate(personalData, result);

        if (result.hasErrors()) {
            throw new PersonalDataException(result.getFieldError().getDefaultMessage());
        } else {
            return ResponseEntity.ok(personalDataService.updatePersonalData(personalData, id));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePersonalData(@PathVariable("id") long id) {
        return ResponseEntity.ok(personalDataService.deletePersonalData(id));
    }
}
