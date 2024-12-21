package MedicalWebsite.MedicalWebsite.DTO.PatientsDTO;

import java.time.Instant;
import lombok.Data;

@Data
public class UpdatePatientDTO {
    Integer patinet_id;
    String firstname;
    String lastname;
    String phonenumber;
    String surname;
    Integer service_id;
    Instant time;
}
