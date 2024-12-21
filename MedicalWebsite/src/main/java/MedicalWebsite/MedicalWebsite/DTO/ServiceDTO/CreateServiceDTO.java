package MedicalWebsite.MedicalWebsite.DTO.ServiceDTO;
import lombok.Data;

@Data
public class CreateServiceDTO {
    Integer service_id;
    String serviceName;
    String serviceDescription;
}
