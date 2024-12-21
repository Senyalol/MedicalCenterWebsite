package MedicalWebsite.MedicalWebsite.Repository;

import MedicalWebsite.MedicalWebsite.Entites.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment findById(int id);
}
