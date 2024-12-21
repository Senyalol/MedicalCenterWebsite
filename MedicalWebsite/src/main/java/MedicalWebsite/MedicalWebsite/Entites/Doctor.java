package MedicalWebsite.MedicalWebsite.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_gen")
    @SequenceGenerator(name = "doctors_id_gen", sequenceName = "doctors_doctorid_seq", allocationSize = 1)
    @Column(name = "doctor_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Size(max = 50)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Size(max = 100)
    @NotNull
    @Column(name = "specialty", nullable = false, length = 100)
    private String specialty;

    @Size(max = 15)
    @Column(name = "phonenumber", length = 15)
    private String phonenumber;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 100)
    @Column(name = "category", length = 100)
    private String category;

}