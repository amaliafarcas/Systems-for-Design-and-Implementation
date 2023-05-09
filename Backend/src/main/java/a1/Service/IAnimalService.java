package a1.Service;

import a1.DTO.getAll.AnimalIdDTO;
import a1.DTO.getIndividual.AnimalDTO;
import a1.DTO.join.AnimalForVolunteersDTO;
import a1.Domain.Animal;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {
    List<AnimalIdDTO> getAnimals();
    AnimalDTO getAnimalById(Long entityId);
    AnimalForVolunteersDTO addNewAnimal(AnimalForVolunteersDTO entityDTO);
    void deleteAnimal(Long entityId);
    void updateAnimal(AnimalForVolunteersDTO entityDTO);

    List<AnimalIdDTO> findAllList(int pageNumber, int pageSize);

    Page<AnimalIdDTO> findAll(int page, int size);

    Long getCount();
}
