package a1.DTO.getIndividual;

import a1.DTO.join.AnimalForVolunteersDTO;
import a1.DTO.join.VolunteerForAnimalsDTO;
import a1.Domain.Animal;
import a1.Domain.Volunteer;
import a1.Domain.VolunteerAnimal;

import java.time.LocalDate;

public class VolunteerAnimalDTO {

    private AnimalForVolunteersDTO animalId;
    private VolunteerForAnimalsDTO volunteerId;
    private LocalDate dayAssigned;

    public VolunteerAnimalDTO(AnimalForVolunteersDTO animalId, VolunteerForAnimalsDTO volunteerId, LocalDate dayAssigned) {
        this.animalId = animalId;
        this.volunteerId = volunteerId;
        this.dayAssigned = dayAssigned;
    }

    public VolunteerAnimalDTO(VolunteerAnimal volunteerAnimal) {
        this.animalId = new AnimalForVolunteersDTO(volunteerAnimal.getAnimal());
        this.volunteerId = new VolunteerForAnimalsDTO(volunteerAnimal.getVolunteer());
        this.dayAssigned = volunteerAnimal.getAssignmentDay();
    }

    public AnimalForVolunteersDTO getAnimalId() {
        return animalId;
    }

    public void setAnimalId(AnimalForVolunteersDTO animalId) {
        this.animalId = animalId;
    }

    public VolunteerForAnimalsDTO getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(VolunteerForAnimalsDTO volunteerId) {
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
        return "VolunteerAnimalDTO{" +
                "animalId=" + animalId +
                ", volunteerId=" + volunteerId +
                ", dayAssigned=" + dayAssigned +
                '}';
    }
}
