package MedicalWebsite.MedicalWebsite.Controllers;

import MedicalWebsite.MedicalWebsite.Services.MedicalService;
import MedicalWebsite.MedicalWebsite.DTO.MedicalrecordsDTO.CreateMedicalrecordsDTO;
import MedicalWebsite.MedicalWebsite.DTO.MedicalrecordsDTO.ShortMedicalRecordsDTO;
import MedicalWebsite.MedicalWebsite.DTO.MedicalrecordsDTO.UpdateMedicalrecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/medicalrecords")
public class MedicalController {

    private final MedicalService medicalService;

    @Autowired
    public MedicalController(MedicalService medicalService) {
        this.medicalService = medicalService;
    }

    @GetMapping
    public List<ShortMedicalRecordsDTO> getAllMedicalRecords() {
        return medicalService.getMedicalRecords();
    }

    @GetMapping("/{id}")
    public ShortMedicalRecordsDTO getMedicalRecordById(@PathVariable int id) {
        return medicalService.getMedicalRecordById(id);
    }

    @PostMapping
    public ResponseEntity<ShortMedicalRecordsDTO> createMedicalRecord(@RequestBody @Valid CreateMedicalrecordsDTO createMedicalrecordsDTO) {
        ShortMedicalRecordsDTO createdRecord = medicalService.createMedicalRecord(createMedicalrecordsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord); // Возвращаем созданную запись
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMedicalRecord(@PathVariable("id") int id, @Valid @RequestBody UpdateMedicalrecordsDTO updateMedicalrecordsDTO) {
        try {
            medicalService.updateMedicalRecord(id, updateMedicalrecordsDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable("id") int id) {
        try {
            medicalService.deleteMedicalRecord(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}