package MedicalWebsite.MedicalWebsite.Repository;

import MedicalWebsite.MedicalWebsite.Entites.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findById(int id);
}
