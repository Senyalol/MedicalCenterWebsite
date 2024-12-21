package MedicalWebsite.MedicalWebsite.Controllers;

import MedicalWebsite.MedicalWebsite.Services.SuperService;
import MedicalWebsite.MedicalWebsite.DTO.ServiceDTO.CreateServiceDTO;
import MedicalWebsite.MedicalWebsite.DTO.ServiceDTO.ShortServiceInfoDTO;
import MedicalWebsite.MedicalWebsite.DTO.ServiceDTO.UpdateServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final SuperService superService;

    @Autowired
    public ServiceController(SuperService superService) {
        this.superService = superService;
    }

    // Method to get all services
    @GetMapping
    public List<ShortServiceInfoDTO> getAllServices() {
        return superService.getAllServices();
    }

    // Method to create a new service
    @PostMapping
    public ShortServiceInfoDTO createService(@RequestBody @Valid CreateServiceDTO createServiceDTO) {
        return superService.createService(createServiceDTO);
    }

    // Method to update an existing service by ID
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateService(@PathVariable("id") int id, @Valid @RequestBody UpdateServiceDTO updateServiceDTO) {
        try {
            superService.updateService(id, updateServiceDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Method to delete a service by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable("id") int id) {
        try {
            superService.deleteService(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}