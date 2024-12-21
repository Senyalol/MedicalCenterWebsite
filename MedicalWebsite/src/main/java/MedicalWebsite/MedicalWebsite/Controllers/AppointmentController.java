package MedicalWebsite.MedicalWebsite.Controllers;

import MedicalWebsite.MedicalWebsite.Services.AppointmentService;
import MedicalWebsite.MedicalWebsite.DTO.AppointmentsDTO.CreateAppointmentsDTO;
import MedicalWebsite.MedicalWebsite.DTO.AppointmentsDTO.ShortAppointmentInfoDTO;
import MedicalWebsite.MedicalWebsite.DTO.AppointmentsDTO.UpdateAppointmentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<ShortAppointmentInfoDTO> getAllAppointments() {
        return appointmentService.getAppointments();
    }

    @GetMapping("/{id}")
    public ShortAppointmentInfoDTO getAppointmentById(@PathVariable int id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping
    public ResponseEntity<ShortAppointmentInfoDTO> createAppointment(@RequestBody @Valid CreateAppointmentsDTO createAppointmentsDTO) {
        ShortAppointmentInfoDTO createdAppointment = appointmentService.createAppointment(createAppointmentsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment); // Возвращаем созданную запись
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable("id") int id, @Valid @RequestBody UpdateAppointmentsDTO updateAppointmentsDTO) {
        try {
            appointmentService.updateAppointment(id, updateAppointmentsDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") int id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}