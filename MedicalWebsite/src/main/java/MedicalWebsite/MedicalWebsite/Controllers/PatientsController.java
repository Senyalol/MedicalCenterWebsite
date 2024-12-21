package MedicalWebsite.MedicalWebsite.Controllers;

import MedicalWebsite.MedicalWebsite.Services.PatientService;
import MedicalWebsite.MedicalWebsite.DTO.PatientsDTO.CreatePatientDTO;
import MedicalWebsite.MedicalWebsite.DTO.PatientsDTO.ShortPatientDTO;
import MedicalWebsite.MedicalWebsite.DTO.PatientsDTO.UpdatePatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    private final PatientService patientService;

    @Autowired
    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<ShortPatientDTO> getAllPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public ShortPatientDTO getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public ResponseEntity<ShortPatientDTO> createPatient(@RequestBody @Valid CreatePatientDTO createPatientDTO) {
        ShortPatientDTO createdPatient = patientService.createPatient(createPatientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient); // Возвращаем созданного пациента
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePatient(@PathVariable("id") int id, @Valid @RequestBody UpdatePatientDTO updatePatientDTO) {
        try {
            patientService.updatePatient(id, updatePatientDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") int id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}