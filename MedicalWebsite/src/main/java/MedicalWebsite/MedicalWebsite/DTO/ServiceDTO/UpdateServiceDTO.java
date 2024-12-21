package MedicalWebsite.MedicalWebsite.DTO.ServiceDTO;
import lombok.Data;

@Data
public class UpdateServiceDTO {
    Integer service_id;
    String serviceName;
    String serviceDescription;
}
