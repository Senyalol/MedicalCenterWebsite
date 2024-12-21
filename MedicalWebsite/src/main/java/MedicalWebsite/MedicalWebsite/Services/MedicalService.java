package MedicalWebsite.MedicalWebsite.Services;

import MedicalWebsite.MedicalWebsite.DTO.MedicalrecordsDTO.CreateMedicalrecordsDTO;
import MedicalWebsite.MedicalWebsite.DTO.MedicalrecordsDTO.ShortMedicalRecordsDTO;
import MedicalWebsite.MedicalWebsite.DTO.MedicalrecordsDTO.UpdateMedicalrecordsDTO;
import MedicalWebsite.MedicalWebsite.Entites.Medicalrecord;
import MedicalWebsite.MedicalWebsite.Repository.MedicalrecordsRepository;
import MedicalWebsite.MedicalWebsite.Repository.PatientRepository;
import MedicalWebsite.MedicalWebsite.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MedicalService {

    private final MedicalrecordsRepository medicalrecordsRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public MedicalService(MedicalrecordsRepository medicalrecordsRepository,
                          PatientRepository patientRepository,
                          DoctorRepository doctorRepository) {
        this.medicalrecordsRepository = medicalrecordsRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<ShortMedicalRecordsDTO> getMedicalRecords() {
        List<Medicalrecord> records = medicalrecordsRepository.findAll();

        return records.stream()
                .map(record -> {
                    ShortMedicalRecordsDTO recordDTO = new ShortMedicalRecordsDTO();
                    recordDTO.setMedicalrecord_id(record.getId());
                    recordDTO.setPatient_id(record.getPatient().getId());
                    recordDTO.setDoctor_id(record.getDoctor().getId());
                    recordDTO.setVisitdate(record.getVisitdate());
                    recordDTO.setDiagnosis(record.getDiagnosis());
                    recordDTO.setTreatment(record.getTreatment());
                    recordDTO.setNotes(record.getNotes());
                    return recordDTO;
                }).toList();
    }

    public ShortMedicalRecordsDTO getMedicalRecordById(int id) {
        Medicalrecord record = medicalrecordsRepository.findMedicalrecordById(id);

        ShortMedicalRecordsDTO recordDTO = new ShortMedicalRecordsDTO();
        recordDTO.setMedicalrecord_id(record.getId());
        recordDTO.setPatient_id(record.getPatient().getId());
        recordDTO.setDoctor_id(record.getDoctor().getId());
        recordDTO.setVisitdate(record.getVisitdate());
        recordDTO.setDiagnosis(record.getDiagnosis());
        recordDTO.setTreatment(record.getTreatment());
        recordDTO.setNotes(record.getNotes());

        return recordDTO;
    }

    public ShortMedicalRecordsDTO createMedicalRecord(CreateMedicalrecordsDTO createMedicalrecordsDTO) {
        Medicalrecord medicalRecord = new Medicalrecord();
        medicalRecord.setVisitdate(createMedicalrecordsDTO.getVisitdate());
        medicalRecord.setDiagnosis(createMedicalrecordsDTO.getDiagnosis());
        medicalRecord.setTreatment(createMedicalrecordsDTO.getTreatment());
        medicalRecord.setNotes(createMedicalrecordsDTO.getNotes());

        // Получите пациента и врача из базы данных по их ID
        var patient = patientRepository.findById(createMedicalrecordsDTO.getPatient_id())
                .orElseThrow(() -> new NoSuchElementException("Patient not found"));
        var doctor = doctorRepository.findById(createMedicalrecordsDTO.getDoctor_id())
                .orElseThrow(() -> new NoSuchElementException("Doctor not found"));

        medicalRecord.setPatient(patient);
        medicalRecord.setDoctor(doctor);

        Medicalrecord savedRecord = medicalrecordsRepository.save(medicalRecord);

        // Создаем DTO для возвращения
        ShortMedicalRecordsDTO recordDTO = new ShortMedicalRecordsDTO();
        recordDTO.setMedicalrecord_id(savedRecord.getId());
        recordDTO.setPatient_id(savedRecord.getPatient().getId());
        recordDTO.setDoctor_id(savedRecord.getDoctor().getId());
        recordDTO.setVisitdate(savedRecord.getVisitdate());
        recordDTO.setDiagnosis(savedRecord.getDiagnosis());
        recordDTO.setTreatment(savedRecord.getTreatment());
        recordDTO.setNotes(savedRecord.getNotes());

        return recordDTO; // Возвращаем DTO с созданной медицинской записью
    }

    public void updateMedicalRecord(int id, UpdateMedicalrecordsDTO updateMedicalrecordsDTO) {
        Medicalrecord recordToUpdate = medicalrecordsRepository.findMedicalrecordById(id);

        if (updateMedicalrecordsDTO.getVisitdate() != null) {
            recordToUpdate.setVisitdate(updateMedicalrecordsDTO.getVisitdate());
        }
        if (updateMedicalrecordsDTO.getDiagnosis() != null) {
            recordToUpdate.setDiagnosis(updateMedicalrecordsDTO.getDiagnosis());
        }
        if (updateMedicalrecordsDTO.getTreatment() != null) {
            recordToUpdate.setTreatment(updateMedicalrecordsDTO.getTreatment());
        }
        if (updateMedicalrecordsDTO.getNotes() != null) {
            recordToUpdate.setNotes(updateMedicalrecordsDTO.getNotes());
        }

        medicalrecordsRepository.save(recordToUpdate);
    }

    public void deleteMedicalRecord(int id) {
        Medicalrecord recordToDelete = medicalrecordsRepository.findMedicalrecordById(id);
        medicalrecordsRepository.delete(recordToDelete);
    }
}