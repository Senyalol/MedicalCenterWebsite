package MedicalWebsite.MedicalWebsite.Controllers;

import MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO.CreateDoctorDTO;
import MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO.ShortDoctorInfoDTO;
import MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO.UpdateDoctorDTO;
import MedicalWebsite.MedicalWebsite.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Создание нового врача
    @PostMapping
    public ResponseEntity<ShortDoctorInfoDTO> createDoctor(@RequestBody CreateDoctorDTO createDoctorDTO) {
        ShortDoctorInfoDTO createdDoctor = doctorService.createDoctor(createDoctorDTO);
        return ResponseEntity.status(201).body(createdDoctor); // 201 Created
    }

    // Получение всех врачей
    @GetMapping
    public ResponseEntity<List<ShortDoctorInfoDTO>> getAllDoctors() {
        List<ShortDoctorInfoDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors); // 200 OK
    }

    // Получение врача по ID
    @GetMapping("/{id}")
    public ResponseEntity<ShortDoctorInfoDTO> getDoctorById(@PathVariable Integer id) {
        ShortDoctorInfoDTO doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor); // 200 OK
    }

    // Частичное обновление информации о враче
    @PatchMapping("/{id}")
    public ResponseEntity<ShortDoctorInfoDTO> partialUpdateDoctor(@PathVariable Integer id,
                                                                  @RequestBody UpdateDoctorDTO updateDoctorDTO) {
        ShortDoctorInfoDTO updatedDoctor = doctorService.partialUpdateDoctor(id, updateDoctorDTO);
        return ResponseEntity.ok(updatedDoctor); // 200 OK
    }

    // Удаление врача по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}