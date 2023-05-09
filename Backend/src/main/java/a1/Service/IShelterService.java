package a1.Service;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.getIndividual.ShelterListDTO;
import a1.DTO.join.MedicalRecordDTO;
import a1.DTO.join.ShelterDTO;
import a1.DTO.reports.AverageAgeVolunteersShelterDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IShelterService {
    List<ShelterIdDTO> getShelters();
    ShelterListDTO getShelterById(Long shelterId);
    void addNewShelter(ShelterDTO shelterDTO);
    void deleteShelter(Long shelterId);
    void updateShelter(ShelterDTO shelterDTO);
    Page<ShelterIdDTO> getSheltersGreaterCapacity(Integer capacity, int pageNumber, int pageSize);
    Page<ShelterIdDTO> findAll(int page, int size);

    Page<AverageAgeVolunteersShelterDTO> exportReportVolunteer2(int pageNumber, int pageSize);

    Long getCount();
}
