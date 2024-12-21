package MedicalWebsite.MedicalWebsite.DTO.AppointmentsDTO;

import java.time.Instant;
import lombok.Data;

@Data
public class CreateAppointmentsDTO {

    Integer appointment_id;
    Integer patient_id;
    Integer doctor_id;
    Instant appointmentdate;
    String reason;
    String status;

}
