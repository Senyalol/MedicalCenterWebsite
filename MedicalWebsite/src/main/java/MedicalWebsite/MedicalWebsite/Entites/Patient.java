package MedicalWebsite.MedicalWebsite.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id_gen")
    @SequenceGenerator(name = "patients_id_gen", sequenceName = "patients_patientid_seq", allocationSize = 1)
    @Column(name = "patient_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Size(max = 50)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Size(max = 15)
    @Column(name = "phonenumber", length = 15)
    private String phonenumber;

    @Size(max = 255)
    @Column(name = "surname")
    private String surname;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "\"time\"")
    private Instant time;

}