package a1.Controller;

import a1.DTO.getAll.VolunteerAnimalIdDTO;
import a1.DTO.getIndividual.VolunteerAnimalDTO;
import a1.DTO.join.MedicalRecordDTO;
import a1.Domain.Animal;
import a1.Domain.Volunteer;
import a1.Domain.VolunteerAnimal;
import a1.Service.VolunteerAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/volunteerAnimal")
public class VolunteerAnimalController {

    VolunteerAnimalService volunteerAnimalService;

    @Autowired
    public VolunteerAnimalController(VolunteerAnimalService volunteerAnimalService) {
        this.volunteerAnimalService = volunteerAnimalService;
    }

    @GetMapping
    public ResponseEntity<Page<VolunteerAnimalIdDTO>> getAllShelters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<VolunteerAnimalIdDTO> items = volunteerAnimalService.findAll(page, size);
        return ResponseEntity.ok(items);
    }

/*
    @GetMapping
    public List<VolunteerAnimalIdDTO> getVolunteerAnimal(){
        return volunteerAnimalService.getVolunteerAnimal();
    }
*/

    @GetMapping(path="{animalId}/{volunteerId}")
    public VolunteerAnimalDTO getVolunteerAnimalByIds(@PathVariable("animalId") Long animalID, @PathVariable("volunteerId") Long volunteerId){
        return volunteerAnimalService.getVolunteerAnimalByIds(animalID, volunteerId);
    }

    @PostMapping
    public  void addNewVolunteerAnimal(@RequestBody VolunteerAnimalIdDTO volunteerAnimal){
        volunteerAnimalService.addNewVolunteerAnimal(volunteerAnimal);
    }

    @PostMapping(path="/bulk")
    public  void addNewVolunteerAnimalBulk(@RequestBody List<VolunteerAnimalIdDTO> volunteerAnimal){
        for(VolunteerAnimalIdDTO v: volunteerAnimal)
            volunteerAnimalService.addNewVolunteerAnimal(v);
    }

    @DeleteMapping(path="{animalId}/{volunteerId}")
    public void deleteVolunteerAnimal(@PathVariable("animalId") Long animalID, @PathVariable("volunteerId") Long volunteerId){
        volunteerAnimalService.deleteVolunteerAnimal(animalID, volunteerId);
    }

    @PutMapping
    public void updateVolunteerAnimal(@RequestBody VolunteerAnimalIdDTO volunteerAnimal){
        volunteerAnimalService.updateVolunteerAnimal(volunteerAnimal);
    }
}
