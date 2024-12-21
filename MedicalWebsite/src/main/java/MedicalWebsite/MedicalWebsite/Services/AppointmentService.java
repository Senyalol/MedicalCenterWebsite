package MedicalWebsite.MedicalWebsite.Services;

import MedicalWebsite.MedicalWebsite.DTO.AppointmentsDTO.CreateAppointmentsDTO;
import MedicalWebsite.MedicalWebsite.DTO.AppointmentsDTO.ShortAppointmentInfoDTO;
import MedicalWebsite.MedicalWebsite.DTO.AppointmentsDTO.UpdateAppointmentsDTO;
import MedicalWebsite.MedicalWebsite.Entites.Appointment;
import MedicalWebsite.MedicalWebsite.Repository.AppointmentRepository;
import MedicalWebsite.MedicalWebsite.Repository.PatientRepository;
import MedicalWebsite.MedicalWebsite.Repository.DoctorRepository; // Добавьте этот импорт
import MedicalWebsite.MedicalWebsite.Entites.Patient;
import MedicalWebsite.MedicalWebsite.Entites.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository; // Добавьте это поле
    private final DoctorRepository doctorRepository; // Добавьте это поле

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository; // Инициализация
        this.doctorRepository = doctorRepository;   // Инициализация
    }

    public List<ShortAppointmentInfoDTO> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream()
                .map(appointment -> {
                    ShortAppointmentInfoDTO appointmentDTO = new ShortAppointmentInfoDTO();
                    appointmentDTO.setAppointment_id(appointment.getId());
                    appointmentDTO.setPatient_id(appointment.getPatient().getId());
                    appointmentDTO.setDoctor_id(appointment.getDoctor().getId());
                    appointmentDTO.setAppointmentdate(appointment.getAppointmentdate());
                    appointmentDTO.setReason(appointment.getReason());
                    appointmentDTO.setStatus(appointment.getStatus());

                    return appointmentDTO;
                }).toList();
    }

    public ShortAppointmentInfoDTO getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id);

        ShortAppointmentInfoDTO appointmentDTO = new ShortAppointmentInfoDTO();
        appointmentDTO.setAppointment_id(appointment.getId());
        appointmentDTO.setPatient_id(appointment.getPatient().getId());
        appointmentDTO.setDoctor_id(appointment.getDoctor().getId());
        appointmentDTO.setAppointmentdate(appointment.getAppointmentdate());
        appointmentDTO.setReason(appointment.getReason());
        appointmentDTO.setStatus(appointment.getStatus());

        return appointmentDTO;
    }

    public ShortAppointmentInfoDTO createAppointment(CreateAppointmentsDTO createAppointmentsDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentdate(createAppointmentsDTO.getAppointmentdate());
        appointment.setReason(createAppointmentsDTO.getReason());
        appointment.setStatus(createAppointmentsDTO.getStatus());

        // Получите пациента и врача из базы данных по их ID
        Patient patient = patientRepository.findById(createAppointmentsDTO.getPatient_id())
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        Doctor doctor = doctorRepository.findById(createAppointmentsDTO.getDoctor_id())
                .orElseThrow(() -> new NoSuchElementException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Создаем DTO для возвращения
        ShortAppointmentInfoDTO appointmentDTO = new ShortAppointmentInfoDTO();
        appointmentDTO.setAppointment_id(savedAppointment.getId());
        appointmentDTO.setPatient_id(savedAppointment.getPatient().getId());
        appointmentDTO.setDoctor_id(savedAppointment.getDoctor().getId());
        appointmentDTO.setAppointmentdate(savedAppointment.getAppointmentdate());
        appointmentDTO.setReason(savedAppointment.getReason());
        appointmentDTO.setStatus(savedAppointment.getStatus());

        return appointmentDTO; // Возвращаем DTO с созданным приемом
    }

    public void updateAppointment(int id, UpdateAppointmentsDTO updateAppointmentsDTO) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id);

        if (updateAppointmentsDTO.getAppointmentdate() != null) {
            appointmentToUpdate.setAppointmentdate(updateAppointmentsDTO.getAppointmentdate());
        }
        if (updateAppointmentsDTO.getReason() != null) {
            appointmentToUpdate.setReason(updateAppointmentsDTO.getReason());
        }
        if (updateAppointmentsDTO.getStatus() != null) {
            appointmentToUpdate.setStatus(updateAppointmentsDTO.getStatus());
        }

        appointmentRepository.save(appointmentToUpdate);
    }

    public void deleteAppointment(int id) {
        Appointment appointmentToDelete = appointmentRepository.findById(id);

        appointmentRepository.delete(appointmentToDelete);
    }
}