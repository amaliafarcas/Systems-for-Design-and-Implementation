package a1.Service;

import a1.DTO.getAll.VolunteerIdDTO;
import a1.DTO.getIndividual.VolunteerDTO;
import a1.DTO.join.VolunteerForAnimalsDTO;
import a1.DTO.update.VolunteerReadDTO;
import a1.Domain.CustomPageable;
import a1.Domain.Volunteer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IVolunteerService {
    Long getCount();

    Page<VolunteerIdDTO> findAll(int pageNumber, int pageSize);

    List<VolunteerIdDTO> getVolunteers();

    VolunteerDTO getVolunteerById(Long volunteerId);

    void addNewVolunteer(VolunteerForAnimalsDTO volunteerDTO);

    void deleteVolunteer(Long volunteerId);

    void updateVolunteer(VolunteerReadDTO volunteer);
}
