package project.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import project.core.utils.identification.UserValidator;

@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        return new UserValidator();
    }
}
