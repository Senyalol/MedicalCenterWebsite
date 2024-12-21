package MedicalWebsite.MedicalWebsite.Services;

import MedicalWebsite.MedicalWebsite.DTO.ServiceDTO.CreateServiceDTO;
import MedicalWebsite.MedicalWebsite.DTO.ServiceDTO.ShortServiceInfoDTO;
import MedicalWebsite.MedicalWebsite.DTO.ServiceDTO.UpdateServiceDTO;
import MedicalWebsite.MedicalWebsite.Repository.ServiceRepository;
import MedicalWebsite.MedicalWebsite.Entites.MegaService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class SuperService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public SuperService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ShortServiceInfoDTO> getAllServices() {
        List<MegaService> megaServices = serviceRepository.findAll();
        return megaServices.stream()
                .map(megaService -> {
                    ShortServiceInfoDTO serviceDTO = new ShortServiceInfoDTO();
                    serviceDTO.setService_id(megaService.getId());
                    serviceDTO.setServiceName(megaService.getServiceName());
                    serviceDTO.setServiceDescription(megaService.getServiceDescription());
                    return serviceDTO;
                })
                .collect(Collectors.toList());
    }

    public ShortServiceInfoDTO createService(CreateServiceDTO createServiceDTO) {
        MegaService megaService = new MegaService();
        megaService.setServiceName(createServiceDTO.getServiceName());
        megaService.setServiceDescription(createServiceDTO.getServiceDescription());

        MegaService savedMegaService = serviceRepository.save(megaService);

        ShortServiceInfoDTO serviceDTO = new ShortServiceInfoDTO();
        serviceDTO.setService_id(savedMegaService.getId());
        serviceDTO.setServiceName(savedMegaService.getServiceName());
        serviceDTO.setServiceDescription(savedMegaService.getServiceDescription());

        return serviceDTO;
    }

    public void updateService(int id, UpdateServiceDTO updateServiceDTO) {
        MegaService megaServiceToUpdate = serviceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Service with ID " + id + " not found"));

        if (updateServiceDTO.getServiceName() != null) {
            megaServiceToUpdate.setServiceName(updateServiceDTO.getServiceName());
        }
        if (updateServiceDTO.getServiceDescription() != null) {
            megaServiceToUpdate.setServiceDescription(updateServiceDTO.getServiceDescription());
        }

        serviceRepository.save(megaServiceToUpdate);
    }

    public void deleteService(int id) {
        MegaService megaServiceToDelete = serviceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Service with ID " + id + " not found"));

        serviceRepository.delete(megaServiceToDelete);
    }
}