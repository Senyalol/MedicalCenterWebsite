package MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO;

import lombok.Data;

@Data
public class CreateDoctorDTO {

    Integer doctor_id;
    String firstname;
    String lastname;
    String specialty;
    String phonenumber;
    String email;
    String category;
}
