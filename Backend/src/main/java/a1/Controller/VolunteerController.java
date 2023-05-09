package a1.Controller;

import a1.DTO.getAll.VolunteerAnimalIdDTO;
import a1.DTO.getAll.VolunteerIdDTO;
import a1.DTO.getIndividual.VolunteerDTO;
import a1.DTO.join.VolunteerForAnimalsDTO;
import a1.DTO.update.VolunteerReadDTO;
import a1.Domain.Volunteer;
import a1.Service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/volunteer")
public class VolunteerController {

    VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }


    @GetMapping
    public ResponseEntity<Page<VolunteerIdDTO>> getAllShelters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<VolunteerIdDTO> items = volunteerService.findAll(page, size);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/totalRecords")
    public Long getCount(){
        return volunteerService.getCount();
    }

    @GetMapping(path = "{volunteerId}")
    public VolunteerDTO getVolunteerById(@PathVariable("volunteerId") Long volunteerId){
        return volunteerService.getVolunteerById(volunteerId);
    }

    @PostMapping
    public void addNewVolunteer(@RequestBody VolunteerForAnimalsDTO volunteer){
        volunteerService.addNewVolunteer(volunteer);
    }

    @DeleteMapping(path = "{volunteerId}")
    public void deleteVolunteer(@PathVariable("volunteerId") Long volunteerId){
        volunteerService.deleteVolunteer(volunteerId);
    }

    @PutMapping
    public void updateVolunteer(@RequestBody VolunteerReadDTO volunteer){
        volunteerService.updateVolunteer(volunteer);
    }

}
