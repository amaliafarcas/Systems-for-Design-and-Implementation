package a1.Service;

import a1.DTO.getAll.VolunteerAnimalIdDTO;
import a1.DTO.getAll.VolunteerIdDTO;
import a1.DTO.getIndividual.VolunteerAnimalDTO;
import a1.DTO.join.MedicalRecordDTO;
import a1.Domain.*;
import a1.Repository.VolunteerAnimalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerAnimalService implements IVolunteerAnimalService{

    @PersistenceContext
    private EntityManager entityManager;
    private VolunteerAnimalRepository volunteerAnimalRepository;

    @Autowired
    public VolunteerAnimalService(VolunteerAnimalRepository volunteerAnimalRepository) {
        this.volunteerAnimalRepository = volunteerAnimalRepository;
    }


    public Page<VolunteerAnimalIdDTO> findAll(int pageNumber, int pageSize) {
        CustomPageable pageable = new CustomPageable(pageNumber, pageSize);

        List<VolunteerAnimalIdDTO> volunteerAnimals = new ArrayList<>();
        for(VolunteerAnimal a: volunteerAnimalRepository.findAll(pageable)){
            volunteerAnimals.add(new VolunteerAnimalIdDTO(a));
        }

        return new PageImpl<>(volunteerAnimals, pageable, volunteerAnimals.size());
    }


    public List<VolunteerAnimalIdDTO> getVolunteerAnimal() {
        List<VolunteerAnimalIdDTO> volunteerAnimal = new ArrayList<>();
        for(VolunteerAnimal a: volunteerAnimalRepository.findAll()){
            volunteerAnimal.add(new VolunteerAnimalIdDTO(a.getAnimal().getId(), a.getVolunteer().getId(), a.getAssignmentDay()));
        }

        return volunteerAnimal;
    }

    public VolunteerAnimalDTO getVolunteerAnimalByIds(Long animalID, Long volunteerId) {
        VolunteerAnimalPrimaryKey primaryKey = new VolunteerAnimalPrimaryKey();
        primaryKey.setAnimalId(animalID);
        primaryKey.setVolunteerId(volunteerId);

        VolunteerAnimal checkVolunteerAnimal = entityManager.find(VolunteerAnimal.class, primaryKey);

        if(checkVolunteerAnimal == null) {
            throw new IllegalStateException("No entity exists for these values animal/volunteer "+ animalID+", "+ volunteerId);
        }

        return new VolunteerAnimalDTO(checkVolunteerAnimal);
    }

    public void addNewVolunteerAnimal(VolunteerAnimalIdDTO volunteerAnimal) {

        VolunteerAnimalPrimaryKey primaryKey = new VolunteerAnimalPrimaryKey();
        primaryKey.setAnimalId(volunteerAnimal.getAnimalId());
        primaryKey.setVolunteerId(volunteerAnimal.getVolunteerId());

        VolunteerAnimal checkVolunteerAnimal = entityManager.find(VolunteerAnimal.class, primaryKey);
        if(checkVolunteerAnimal != null) {
            throw new IllegalStateException("Entity already exists for these values animal/volunteer "+ volunteerAnimal.getAnimalId()+", "+ volunteerAnimal.getVolunteerId());
        }

        Animal animal = entityManager.find(Animal.class, volunteerAnimal.getAnimalId());
        Volunteer volunteer = entityManager.find(Volunteer.class, volunteerAnimal.getVolunteerId());

        volunteerAnimalRepository.save(new VolunteerAnimal(animal, volunteer, volunteerAnimal.getDayAssigned()));
    }

    @Transactional
    public void deleteVolunteerAnimal(Long animalID, Long volunteerId) {

        VolunteerAnimalPrimaryKey key = new VolunteerAnimalPrimaryKey(animalID, volunteerId);

        VolunteerAnimal volunteerAnimal = entityManager.find(VolunteerAnimal.class, key);
        if(volunteerAnimal != null) {
            entityManager.remove(volunteerAnimal);
        }
        else {
            throw new IllegalStateException("No entity found for these values animal/volunteer "+ animalID+", "+ volunteerId);
        }

    }

    public void updateVolunteerAnimal(VolunteerAnimalIdDTO volunteerAnimal) {

        VolunteerAnimalPrimaryKey primaryKey = new VolunteerAnimalPrimaryKey();
        primaryKey.setAnimalId(volunteerAnimal.getAnimalId());
        primaryKey.setVolunteerId(volunteerAnimal.getVolunteerId());

        VolunteerAnimal updateVolunteerAnimal = entityManager.find(VolunteerAnimal.class, primaryKey);

        if(updateVolunteerAnimal == null) {
            throw new IllegalStateException("No entity found for these values animal/volunteer "+ volunteerAnimal.getAnimalId() + ", "+ volunteerAnimal.getVolunteerId());
        }

        if(volunteerAnimal.getDayAssigned()!=null){
            updateVolunteerAnimal.setAssignmentDay(volunteerAnimal.getDayAssigned());
        }

        volunteerAnimalRepository.save(updateVolunteerAnimal);

    }

}
