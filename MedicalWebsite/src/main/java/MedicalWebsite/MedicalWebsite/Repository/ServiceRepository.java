package MedicalWebsite.MedicalWebsite.Repository;

import MedicalWebsite.MedicalWebsite.Entites.MegaService; // Убедитесь, что импортируете правильный класс
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<MegaService, Integer> {
    // JpaRepository уже предоставляет метод findById и другие
}