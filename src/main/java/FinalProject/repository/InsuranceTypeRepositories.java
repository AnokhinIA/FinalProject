package FinalProject.repository;


import FinalProject.model.InsuranceType;
import FinalProject.model.Insurer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceTypeRepositories extends JpaRepository<InsuranceType, Long> {


}
