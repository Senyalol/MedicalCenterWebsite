package MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO;

import lombok.Data;

@Data
public class UpdateDoctorDTO {
    Integer doctor_id;
    String firstname;
    String lastname;
    String specialty;
    String phonenumber;
    String email;
    String category;
}
