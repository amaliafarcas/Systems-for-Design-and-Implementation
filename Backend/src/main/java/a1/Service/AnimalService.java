package a1.Service;

import a1.DTO.getAll.AnimalIdDTO;
import a1.DTO.getIndividual.AnimalDTO;
import a1.DTO.join.AnimalForVolunteersDTO;
import a1.Domain.Animal;
import a1.Domain.MedicalRecord;
import a1.Domain.Shelter;
import a1.Repository.AnimalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AnimalService implements IAnimalService{

    @PersistenceContext
    private EntityManager entityManager;
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<AnimalIdDTO> findAllList(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));

        List<AnimalIdDTO> animals = new ArrayList<>();
        for(Animal a: animalRepository.findAll(pageable)){
            animals.add(new AnimalIdDTO(a));
        }

        return animals;
    }

    public Page<AnimalIdDTO> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));

        List<AnimalIdDTO> animals = new ArrayList<>();
        for(Animal a: animalRepository.findAll(pageable)){
            animals.add(new AnimalIdDTO(a));
        }

        return new PageImpl<>(animals, pageable, animals.size());
    }

    @Override
    public Long getCount() {
        return animalRepository.count();
    }


    public List<AnimalIdDTO> getAnimals() {
        List<AnimalIdDTO> animals = new ArrayList<>();
        for(Animal a: animalRepository.findAll()){
            animals.add(new AnimalIdDTO(a));
        }

        return animals;
    }

    public AnimalDTO getAnimalById(Long animalId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(()->new IllegalStateException("animal with id" + animalId + " does not exist"));

        return new AnimalDTO(animal);
    }

    public AnimalForVolunteersDTO addNewAnimal(@Valid AnimalForVolunteersDTO animalDTO) {

        Shelter shelter = entityManager.find(Shelter.class, animalDTO.getShelter());
        if (shelter == null) {
            throw new IllegalStateException("Shelter with ID " + animalDTO.getShelter() + " not found");
        }

        MedicalRecord medicalRecord = entityManager.find(MedicalRecord.class, animalDTO.getMedicalRecord());
        if (medicalRecord == null) {
            throw new IllegalStateException("Medical record with ID " + animalDTO.getMedicalRecord() + " not found");
        }

        Optional<Animal> animalOptional = animalRepository.findAnimalByMicrochip(animalDTO.getMicrochipNumber());
        if(animalOptional.isPresent()){
            throw  new IllegalStateException("Microchip number already taken");
        }
        /*return AnimalForVolunteersDTO.from(animalRepository.save(Animal.from(animalDTO)));*/
        return new AnimalForVolunteersDTO(animalRepository.save(new Animal(animalDTO.getMicrochipNumber(), animalDTO.getName(), medicalRecord, shelter, animalDTO.getDayBroughtIn(), animalDTO.getDateAdopted())));

        //return new AnimalForVolunteersDTO(animalRepository.save(animalDTO));

    }

    public void deleteAnimal(Long animalId) {
        boolean exists = animalRepository.existsById(animalId);
        if(!exists){
            throw  new IllegalStateException("animal with id" + animalId + " does not exist");
        }
        animalRepository.deleteById(animalId);
    }

    public void updateAnimal(AnimalForVolunteersDTO animalDTO) {

        Animal animal = entityManager.find(Animal.class, animalDTO.getId());

        if (animal == null) {
            throw new IllegalStateException("Animal with ID " + animalDTO.getId() + " not found");
        }

        if(animalDTO.getName()!=null && animalDTO.getName().length()>0){
            animal.setName(animalDTO.getName());
        }

        if(animalDTO.getMicrochipNumber()!=null && animalDTO.getMicrochipNumber()>0){
            Optional<Animal> animalOptional = animalRepository.findAnimalByMicrochip(animalDTO.getMicrochipNumber());
            if(animalOptional.isPresent()){
                throw  new IllegalStateException("Microchip number already taken");
            }
            animal.setMicrochipNumber(animalDTO.getMicrochipNumber());
        }

        if(animalDTO.getShelter()!=null ){
            Shelter shelter = entityManager.find(Shelter.class, animalDTO.getShelter());
            if (shelter == null) {
                throw new IllegalStateException("Shelter with ID " + animalDTO.getShelter() + " not found");
            }
            animal.setShelterId(shelter);
        }

        if(animalDTO.getMedicalRecord()!=null ){
            MedicalRecord medicalRecord = entityManager.find(MedicalRecord.class, animalDTO.getMedicalRecord());
            if (medicalRecord == null) {
                throw new IllegalStateException("Medical record with ID " + animalDTO.getMedicalRecord() + " not found");
            }
            animal.setMedicalRecordId(medicalRecord);
        }

        if(animalDTO.getDateAdopted()!=null ){
            animal.setDateAdopted(animalDTO.getDateAdopted());
        }


        if(animalDTO.getDayBroughtIn()!=null ){
            animal.setDayBroughtIn(animalDTO.getDayBroughtIn());
        }

        animalRepository.save(animal);
    }

}
