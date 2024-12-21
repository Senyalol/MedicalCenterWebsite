package MedicalWebsite.MedicalWebsite.Repository;

import MedicalWebsite.MedicalWebsite.Entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByLastname(String lastname); // Исправлено на lastname
}