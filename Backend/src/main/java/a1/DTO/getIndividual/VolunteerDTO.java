package a1.DTO.getIndividual;

import a1.DTO.getAll.AnimalIdDTO;
import a1.DTO.join.AnimalForVolunteersDTO;
import a1.DTO.join.TemplatedAnimalVolunteerDTO;
import a1.Domain.Animal;
import a1.Domain.Volunteer;
import a1.Domain.VolunteerAnimal;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class VolunteerDTO {

    private Long id;
    private String name;
    private Long code;
    private LocalDate dateOfBirth;
    private String email;
    private boolean isSpecialist;
    private Set<TemplatedAnimalVolunteerDTO<AnimalForVolunteersDTO>> animalsAssigned;

    public VolunteerDTO(Long id, String name, Long code, LocalDate dateOfBirth, String email, boolean isSpecialist, Set<VolunteerAnimal> animalsAssigned) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isSpecialist = isSpecialist;
        this.animalsAssigned =  this.getAnimalsAssigned(animalsAssigned);
    }

    public VolunteerDTO(Volunteer volunteer) {
        this.id = volunteer.getId();
        this.name = volunteer.getName();
        this.code = volunteer.getCode();
        this.dateOfBirth = volunteer.getDateOfBirth();
        this.email = volunteer.getEmail();
        this.isSpecialist = volunteer.isSpecialist();
        this.animalsAssigned = this.getAnimalsAssigned(volunteer.getAnimalsAssigned());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSpecialist() {
        return isSpecialist;
    }

    public void setSpecialist(boolean specialist) {
        isSpecialist = specialist;
    }

    public Set<TemplatedAnimalVolunteerDTO<AnimalForVolunteersDTO>> getAnimalsAssigned() {
        return animalsAssigned;
    }

    public Set<TemplatedAnimalVolunteerDTO<AnimalForVolunteersDTO>> getAnimalsAssigned(Set<VolunteerAnimal> animalsAssigned) {
        Set<TemplatedAnimalVolunteerDTO<AnimalForVolunteersDTO>> animals = new HashSet<>();
        for(VolunteerAnimal s : animalsAssigned){
            animals.add(new TemplatedAnimalVolunteerDTO<AnimalForVolunteersDTO>(new AnimalForVolunteersDTO(s.getAnimal()), s.getAssignmentDay()));
        }
        return animals;
    }

    public void setAnimalsAssigned(Set<TemplatedAnimalVolunteerDTO<AnimalForVolunteersDTO>> animalsAssigned) {
        this.animalsAssigned = animalsAssigned;
    }

    @Override
    public String toString() {
        return "VolunteerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", isSpecialist=" + isSpecialist +
                ", animalsAssigned=" + animalsAssigned +
                '}';
    }
}
