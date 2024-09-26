package project.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import project.core.utils.validation.profile.PersonalDataValidator;
import project.core.utils.validation.profile.UserValidator;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator userValidatorConfig() {return new UserValidator(); }

    @Bean
    public Validator personalDataValidatorConfig() {
        return new PersonalDataValidator();
    }
}
