package project.core.controllers.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.core.dto.profile.UserDTO;
import project.core.entities.profile.User;
import project.core.exceptions.profile.UserException;
import project.core.services.profile.UserService;
import project.core.utils.validation.profile.UserValidator;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long id,
                                             @Validated @RequestBody UserDTO user, BindingResult result) {

        userValidator.validate(user, result);
        if (result.hasErrors())
            throw new UserException(result.getFieldError().getDefaultMessage());
        else
            return ResponseEntity.ok(userService.updateUser(user, id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
