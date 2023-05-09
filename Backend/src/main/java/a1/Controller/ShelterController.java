package a1.Controller;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.getIndividual.ShelterListDTO;
import a1.DTO.join.ShelterDTO;
import a1.DTO.reports.AverageAgeVolunteersShelterDTO;
import a1.DTO.reports.VaccinatedAnimalsRatioSheltersDTO;
import a1.Service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/shelter")
public class ShelterController {

    private final ShelterService shelterService;


    @Autowired
    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping
    public ResponseEntity<Page<ShelterIdDTO>> getAllShelters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ShelterIdDTO> items = shelterService.findAll(page, size);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/totalRecords")
    public Long getCount(){
        return shelterService.getCount();
    }

        @GetMapping("/recordsGreaterCapacity")
    public Long getCount(@RequestParam(required = true) Integer capacity){
        return shelterService.getCountCapacity(capacity);
    }

    @GetMapping("/greaterCapacity")
    public ResponseEntity<Page<ShelterIdDTO>> getSheltersGreaterCapacity(@RequestParam(required = true) Integer capacity, @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        Page<ShelterIdDTO> items = shelterService.getSheltersGreaterCapacity(capacity, page, size);
        return ResponseEntity.ok(items);
    }

    @GetMapping(path = "{shelterId}")
    public ShelterListDTO getShelterById(@PathVariable("shelterId") Long shelterId){
        return shelterService.getShelterById(shelterId);
    }

    @PostMapping
    public void addNewShelter(@RequestBody ShelterDTO shelter){
        shelterService.addNewShelter(shelter);
    }

    @DeleteMapping(path = "{shelterId}")
    public void deleteShelter(@PathVariable("shelterId") Long shelterId){
        shelterService.deleteShelter(shelterId);
    }

    @PutMapping
    public void updateShelter(@RequestBody ShelterDTO shelter){
        shelterService.updateShelter(shelter);
    }


/*
    @GetMapping("/reportVolunteers")
    public List<AverageAgeVolunteersShelterDTO> generateReportVolunteer(){
        return shelterService.exportReportVolunteer();
    }
*/

    @GetMapping("/reportVolunteers")
    public ResponseEntity<Page<AverageAgeVolunteersShelterDTO>> generateReportVolunteer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<AverageAgeVolunteersShelterDTO> items = shelterService.exportReportVolunteer2(page, size);
        return ResponseEntity.ok(items);
    }

/*    @GetMapping("/reportAnimals")
    public List<VaccinatedAnimalsRatioSheltersDTO> generateReportAnimal(){
        return shelterService.exportReportAnimal();
    }*/

    @GetMapping("/reportAnimals")
    public ResponseEntity<Page<VaccinatedAnimalsRatioSheltersDTO>> generateReportAnimal(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<VaccinatedAnimalsRatioSheltersDTO> items = shelterService.exportReportAnimal2(page, size);
        return ResponseEntity.ok(items);
    }

/*
    @GetMapping(path = "/{query}/")
    public ResponseEntity<Page<ShelterIdDTO>>getSheltersQueryName(
            @PathVariable("query") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ShelterIdDTO> items = shelterService.searchByName(query, page, size);
        return ResponseEntity.ok(items);
    }
*/

}
