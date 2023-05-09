package a1.Service;

import a1.DTO.getAll.VolunteerIdDTO;
import a1.DTO.getIndividual.VolunteerDTO;
import a1.DTO.join.VolunteerForAnimalsDTO;
import a1.DTO.update.VolunteerReadDTO;
import a1.Domain.CustomPageable;
import a1.Domain.Volunteer;
import a1.Repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService implements IVolunteerService{

    VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }


    @Override
    public Long getCount() {
        return volunteerRepository.count();
    }

    public Page<VolunteerIdDTO> findAll(int pageNumber, int pageSize) {
        CustomPageable pageable = new CustomPageable(pageNumber, pageSize);

        List<VolunteerIdDTO> volunteers = new ArrayList<>();
        for(Volunteer a: volunteerRepository.findAll(pageable)){
            volunteers.add(new VolunteerIdDTO(a));
        }

        return new PageImpl<>(volunteers, pageable, volunteers.size());
    }

    public List<VolunteerIdDTO> getVolunteers() {
        List<VolunteerIdDTO> volunteers = new ArrayList<>();
        for(Volunteer a: volunteerRepository.findAll()){
            volunteers.add(new VolunteerIdDTO(a));
        }

        return volunteers;
    }

    public VolunteerDTO getVolunteerById(Long volunteerId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId).orElseThrow(()->new IllegalStateException("Volunteer with id" + volunteerId + " does not exist"));

        return new VolunteerDTO(volunteer);
    }

    public void addNewVolunteer(VolunteerForAnimalsDTO volunteerDTO) {

        Optional<Volunteer> volunteerOptional = volunteerRepository.findVolunteerByEmail(volunteerDTO.getEmail());
        if(volunteerOptional.isPresent()){
            throw new IllegalStateException("Email already exists");
        }

        volunteerOptional = volunteerRepository.findShelterByCode(volunteerDTO.getCode());
        if(volunteerOptional.isPresent()){
            throw new IllegalStateException("Code already exists");
        }

        volunteerRepository.save(new Volunteer(volunteerDTO.getName(), volunteerDTO.getCode(), volunteerDTO.getDateOfBirth(), volunteerDTO.getEmail(), volunteerDTO.isSpecialist()));
    }

    public void deleteVolunteer(Long volunteerId) {
        boolean exists = volunteerRepository.existsById(volunteerId);
        if(!exists){
            throw  new IllegalStateException("volunteer with id" + volunteerId + " does not exist");
        }
        volunteerRepository.deleteById(volunteerId);
    }

    public void updateVolunteer(VolunteerReadDTO volunteer) {
        Volunteer volunteerUpdate = volunteerRepository.findById(volunteer.getId()).orElseThrow(() -> new IllegalStateException("volunteer with id" + volunteer.getId() + "does not exist"));

        if(volunteer.getName()!=null && volunteer.getName().length()>0){
            volunteerUpdate.setName(volunteer.getName());
        }

        if(volunteer.getEmail()!=null && volunteer.getEmail().length()>0 && volunteerRepository.findVolunteerByEmail(volunteer.getEmail()).isEmpty()){
            volunteerUpdate.setEmail(volunteer.getEmail());
        }

        if(volunteer.getCode()!=null && volunteer.getCode()>0 &&  volunteerRepository.findShelterByCode(volunteer.getCode()).isEmpty()){
            volunteerUpdate.setCode(volunteer.getCode());
        }

        if(volunteer.getDateOfBirth()!=null){
            volunteerUpdate.setDateOfBirth(volunteer.getDateOfBirth());
        }
        System.out.println(volunteer.getIsSpecialist());

        if(volunteer.getIsSpecialist()!=null){
            if(volunteer.getIsSpecialist()<=0){
                volunteerUpdate.setSpecialist(false);
            }
            else{
                volunteerUpdate.setSpecialist(true);
            }
        }

        volunteerRepository.save(volunteerUpdate);
    }
}
