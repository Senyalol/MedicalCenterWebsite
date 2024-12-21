package MedicalWebsite.MedicalWebsite.DTO.MedicalrecordsDTO;

import java.time.Instant;
import lombok.Data;

@Data
public class UpdateMedicalrecordsDTO {
    Integer Medicalrecord_id;
    Integer patient_id;
    Integer doctor_id;
    Instant visitdate;
    String diagnosis;
    String treatment;
    String notes;
}
