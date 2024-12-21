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
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_id_gen")
    @SequenceGenerator(name = "appointments_id_gen", sequenceName = "appointments_appointmentid_seq", allocationSize = 1)
    @Column(name = "appointment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private MedicalWebsite.MedicalWebsite.Entites.Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private MedicalWebsite.MedicalWebsite.Entites.Doctor doctor;

    @NotNull
    @Column(name = "appointmentdate", nullable = false)
    private Instant appointmentdate;

    @Size(max = 255)
    @Column(name = "reason")
    private String reason;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

}