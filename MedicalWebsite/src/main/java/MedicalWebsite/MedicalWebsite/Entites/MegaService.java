package MedicalWebsite.MedicalWebsite.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "services")
public class MegaService {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_id_gen")
    @SequenceGenerator(name = "services_id_gen", sequenceName = "services_service_id_seq", allocationSize = 1)
    @Column(name = "service_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Size(max = 255)
    @Column(name = "service_description")
    private String serviceDescription;

}