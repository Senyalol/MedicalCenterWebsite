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
@Table(name = "medicalrecords")
public class Medicalrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicalrecords_id_gen")
    @SequenceGenerator(name = "medicalrecords_id_gen", sequenceName = "medicalrecords_recordid_seq", allocationSize = 1)
    @Column(name = "record_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private MedicalWebsite.MedicalWebsite.Entites.Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private MedicalWebsite.MedicalWebsite.Entites.Doctor doctor;

    @NotNull
    @Column(name = "visitdate", nullable = false)
    private Instant visitdate;

    @Size(max = 255)
    @Column(name = "diagnosis")
    private String diagnosis;

    @Size(max = 255)
    @Column(name = "treatment")
    private String treatment;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

}