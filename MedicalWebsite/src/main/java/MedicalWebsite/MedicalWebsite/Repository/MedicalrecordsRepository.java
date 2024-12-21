package MedicalWebsite.MedicalWebsite.Repository;

import MedicalWebsite.MedicalWebsite.Entites.Medicalrecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalrecordsRepository extends JpaRepository<Medicalrecord, Integer> {
    Medicalrecord findMedicalrecordById(int id);
}
