package project.core.controllers.identification;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.core.dto.identification.UserDTO;
import project.core.exceptions.indentification.RegistrationException;
import project.core.services.identification.RegistrationService;
import project.core.utils.profile.PersonalDataValidator;
import project.core.utils.profile.UserValidator;

@Validated
@RestController
@RequestMapping("/registration")
public class Registration {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PersonalDataValidator personalDataValidator;

    @Autowired
    private RegistrationService registrationService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
        binder.setValidator(personalDataValidator);
    }

    @PostMapping("/api")
    public ResponseEntity<String> registrationByApi(@Valid @RequestBody UserDTO user, BindingResult result) {

        userValidator.validate(user, result);
        personalDataValidator.validate(user, result);

        if (result.hasErrors()) {
            throw new RegistrationException(result.getFieldError().getDefaultMessage());
        } else {
            registrationService.saveUser(user);
            return ResponseEntity.ok("user saved successfully");
        }
    }

    @PostMapping("/form")
    public ResponseEntity<String> registrationByForm(@Valid @ModelAttribute UserDTO user, BindingResult result) {

        userValidator.validate(user, result);
        personalDataValidator.validate(user, result);

        if (result.hasErrors()) {
            throw new RegistrationException(result.getFieldError().getDefaultMessage());
        } else {
            registrationService.saveUser(user);
            return ResponseEntity.ok("user saved successfully");
        }
    }

}
