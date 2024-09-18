package project.core.dto.identification;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PersonalDataDTO {

    private String name;
    private String surname;
    private Character gender;
    private LocalDate dateOfBirth;

}
