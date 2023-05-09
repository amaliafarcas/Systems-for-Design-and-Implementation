package a1.Repository;

import a1.Domain.Shelter;
import a1.Domain.VolunteerAnimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerAnimalRepository extends JpaRepository<VolunteerAnimal, Long> {
    Page<VolunteerAnimal> findAll(Pageable pageable);

}
