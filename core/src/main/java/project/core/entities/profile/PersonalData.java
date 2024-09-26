package project.core.entities.profile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "personal_data")
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    private String name;
    private String surname;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;

}
