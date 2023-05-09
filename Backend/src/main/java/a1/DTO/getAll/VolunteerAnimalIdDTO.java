package a1.DTO.getAll;

import a1.Domain.VolunteerAnimal;

import java.time.LocalDate;

public class VolunteerAnimalIdDTO{

    private Long animalId;
    private Long volunteerId;
    private LocalDate dayAssigned;

    public VolunteerAnimalIdDTO(Long animalId, Long volunteerId, LocalDate dayAssigned) {
        this.animalId = animalId;
        this.volunteerId = volunteerId;
        this.dayAssigned = dayAssigned;
    }

    public VolunteerAnimalIdDTO(VolunteerAnimal volunteerAnimal) {
        this.animalId = volunteerAnimal.getVolunteer().getId();
        this.volunteerId = volunteerAnimal.getVolunteer().getId();
        this.dayAssigned = volunteerAnimal.getAssignmentDay();
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public LocalDate getDayAssigned() {
        return dayAssigned;
    }

    public void setDayAssigned(LocalDate dayAssigned) {
        this.dayAssigned = dayAssigned;
    }

    @Override
    public String toString() {
        return "AnimalVolunteerDTO{" +
                "animalId=" + animalId +
                ", volunteerId=" + volunteerId +
                ", dayAssigned=" + dayAssigned +
                '}';
    }
}
