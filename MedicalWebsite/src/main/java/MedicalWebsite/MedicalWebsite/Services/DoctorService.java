package MedicalWebsite.MedicalWebsite.Services;

import MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO.CreateDoctorDTO;
import MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO.ShortDoctorInfoDTO;
import MedicalWebsite.MedicalWebsite.DTO.DoctorsDTO.UpdateDoctorDTO;
import MedicalWebsite.MedicalWebsite.Entites.Doctor;
import MedicalWebsite.MedicalWebsite.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Создание нового врача
    public ShortDoctorInfoDTO createDoctor(CreateDoctorDTO createDoctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setFirstname(createDoctorDTO.getFirstname());
        doctor.setLastname(createDoctorDTO.getLastname());
        doctor.setSpecialty(createDoctorDTO.getSpecialty());
        doctor.setPhonenumber(createDoctorDTO.getPhonenumber());
        doctor.setEmail(createDoctorDTO.getEmail());
        doctor.setCategory(createDoctorDTO.getCategory());

        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToShortDoctorInfoDTO(savedDoctor);
    }

    // Получение всех врачей
    public List<ShortDoctorInfoDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(this::convertToShortDoctorInfoDTO)
                .collect(Collectors.toList());
    }

    // Получение врача по ID
    public ShortDoctorInfoDTO getDoctorById(Integer id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doctor not found with ID: " + id));
        return convertToShortDoctorInfoDTO(doctor);
    }

    // Частичное обновление информации о враче
    public ShortDoctorInfoDTO partialUpdateDoctor(Integer id, UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doctor not found with ID: " + id));

        // Обновляем только те поля, которые были переданы
        if (updateDoctorDTO.getFirstname() != null) {
            doctor.setFirstname(updateDoctorDTO.getFirstname());
        }
        if (updateDoctorDTO.getLastname() != null) {
            doctor.setLastname(updateDoctorDTO.getLastname());
        }
        if (updateDoctorDTO.getSpecialty() != null) {
            doctor.setSpecialty(updateDoctorDTO.getSpecialty());
        }
        if (updateDoctorDTO.getPhonenumber() != null) {
            doctor.setPhonenumber(updateDoctorDTO.getPhonenumber());
        }
        if (updateDoctorDTO.getEmail() != null) {
            doctor.setEmail(updateDoctorDTO.getEmail());
        }
        if (updateDoctorDTO.getCategory() != null) {
            doctor.setCategory(updateDoctorDTO.getCategory());
        }

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return convertToShortDoctorInfoDTO(updatedDoctor);
    }

    // Удаление врача по ID
    public void deleteDoctor(Integer id) {
        if (!doctorRepository.existsById(id)) {
            throw new NoSuchElementException("Doctor not found with ID: " + id);
        }
        doctorRepository.deleteById(id);
    }

    // Преобразование сущности Doctor в DTO
    private ShortDoctorInfoDTO convertToShortDoctorInfoDTO(Doctor doctor) {
        ShortDoctorInfoDTO dto = new ShortDoctorInfoDTO();
        dto.setDoctor_id(doctor.getId());
        dto.setFirstname(doctor.getFirstname());
        dto.setLastname(doctor.getLastname());
        dto.setSpecialty(doctor.getSpecialty());
        dto.setPhonenumber(doctor.getPhonenumber());
        dto.setEmail(doctor.getEmail());
        dto.setCategory(doctor.getCategory());
        return dto;
    }
}