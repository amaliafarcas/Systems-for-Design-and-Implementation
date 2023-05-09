package a1.Service;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.getAll.VolunteerAnimalIdDTO;
import a1.DTO.getIndividual.VolunteerAnimalDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVolunteerAnimalService {

    List<VolunteerAnimalIdDTO> getVolunteerAnimal();

    VolunteerAnimalDTO getVolunteerAnimalByIds(Long animalID, Long volunteerId);

    void addNewVolunteerAnimal(VolunteerAnimalIdDTO volunteerAnimal);

    void deleteVolunteerAnimal(Long animalID, Long volunteerId);

    void updateVolunteerAnimal(VolunteerAnimalIdDTO volunteerAnimal);

    Page<VolunteerAnimalIdDTO> findAll(int page, int size);

}
