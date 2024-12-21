package MedicalWebsite.MedicalWebsite.Services;

import MedicalWebsite.MedicalWebsite.DTO.PatientsDTO.CreatePatientDTO;
import MedicalWebsite.MedicalWebsite.DTO.PatientsDTO.ShortPatientDTO;
import MedicalWebsite.MedicalWebsite.DTO.PatientsDTO.UpdatePatientDTO;
import MedicalWebsite.MedicalWebsite.Entites.Patient;
import MedicalWebsite.MedicalWebsite.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<ShortPatientDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(patient -> {
                    ShortPatientDTO patientDTO = new ShortPatientDTO();
                    patientDTO.setPatinet_id(patient.getId());
                    patientDTO.setFirstname(patient.getFirstname());
                    patientDTO.setLastname(patient.getLastname());
                    patientDTO.setPhonenumber(patient.getPhonenumber());
                    patientDTO.setSurname(patient.getSurname());
                    patientDTO.setService_id(patient.getServiceId());
                    patientDTO.setTime(patient.getTime());

                    return patientDTO;
                }).toList();
    }

    public ShortPatientDTO getPatientById(int id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));

        ShortPatientDTO patientDTO = new ShortPatientDTO();
        patientDTO.setPatinet_id(patient.getId());
        patientDTO.setFirstname(patient.getFirstname());
        patientDTO.setLastname(patient.getLastname());
        patientDTO.setPhonenumber(patient.getPhonenumber());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setService_id(patient.getServiceId());
        patientDTO.setTime(patient.getTime());

        return patientDTO;
    }

    public ShortPatientDTO createPatient(CreatePatientDTO createPatientDTO) {
        Patient patient = new Patient();
        patient.setFirstname(createPatientDTO.getFirstname());
        patient.setLastname(createPatientDTO.getLastname());
        patient.setPhonenumber(createPatientDTO.getPhonenumber());
        patient.setSurname(createPatientDTO.getSurname());
        patient.setServiceId(createPatientDTO.getService_id());
        patient.setTime(createPatientDTO.getTime());

        Patient savedPatient = patientRepository.save(patient);

        ShortPatientDTO patientDTO = new ShortPatientDTO();
        patientDTO.setPatinet_id(savedPatient.getId());
        patientDTO.setFirstname(savedPatient.getFirstname());
        patientDTO.setLastname(savedPatient.getLastname());
        patientDTO.setPhonenumber(savedPatient.getPhonenumber());
        patientDTO.setSurname(savedPatient.getSurname());
        patientDTO.setService_id(savedPatient.getServiceId());
        patientDTO.setTime(savedPatient.getTime());

        return patientDTO; // Возвращаем DTO с созданным пациентом
    }

    public void updatePatient(int id, UpdatePatientDTO updatePatientDTO) {
        Patient patientToUpdate = patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Patient with ID " + id + " not found"));

        if (updatePatientDTO.getFirstname() != null) {
            patientToUpdate.setFirstname(updatePatientDTO.getFirstname());
        }
        if (updatePatientDTO.getLastname() != null) {
            patientToUpdate.setLastname(updatePatientDTO.getLastname());
        }
        if (updatePatientDTO.getPhonenumber() != null) {
            patientToUpdate.setPhonenumber(updatePatientDTO.getPhonenumber());
        }
        if (updatePatientDTO.getSurname() != null) {
            patientToUpdate.setSurname(updatePatientDTO.getSurname());
        }
        if (updatePatientDTO.getService_id() != null) {
            patientToUpdate.setServiceId(updatePatientDTO.getService_id());
        }
        if (updatePatientDTO.getTime() != null) {
            patientToUpdate.setTime(updatePatientDTO.getTime());
        }

        patientRepository.save(patientToUpdate);
    }

    public void deletePatient(int id) {
        Patient patientToDelete = patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Patient with ID " + id + " not found"));

        patientRepository.delete(patientToDelete);
    }
}