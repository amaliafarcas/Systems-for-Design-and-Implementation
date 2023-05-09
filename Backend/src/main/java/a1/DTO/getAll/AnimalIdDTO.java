package a1.DTO.getAll;

import a1.DTO.join.ShelterDTO;
import a1.DTO.join.TemplatedAnimalVolunteerDTO;
import a1.Domain.Animal;
import a1.Domain.MedicalRecord;
import a1.Domain.VolunteerAnimal;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AnimalIdDTO {

    private Long id;
    private Long microchipNumber;
    private String name;
    private Long medicalRecord;
    private Long shelter;
    private LocalDate dayBroughtIn;
    private LocalDate dateAdopted;
    private Set<TemplatedAnimalVolunteerDTO<Long>> volunteersAssigned;


    public AnimalIdDTO(Long id, Long microchipNumber, String name, MedicalRecord medicalRecord, ShelterDTO shelter, LocalDate dayBroughtIn, LocalDate dateAdopted, Set<VolunteerAnimal> volunteersAssigned) {
        this.id = id;
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.medicalRecord = medicalRecord.getId();
        this.shelter = shelter.getId();
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
        this.volunteersAssigned = getVolunteersAssigned(volunteersAssigned);
    }

    public AnimalIdDTO(Animal animal) {
        this.id = animal.getId();
        this.microchipNumber = animal.getMicrochipNumber();
        this.name = animal.getName();
        this.medicalRecord = animal.getMedicalRecordId().getId();
        this.shelter = animal.getShelterId().getId();
        this.dayBroughtIn = animal.getDayBroughtIn();
        this.dateAdopted = animal.getDateAdopted();
        this.volunteersAssigned = getVolunteersAssigned(animal.getVolunteersAssigned());
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMicrochipNumber() {
        return microchipNumber;
    }

    public void setMicrochipNumber(Long microchipNumber) {
        this.microchipNumber = microchipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(Long medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Long getShelter() {
        return shelter;
    }

    public void setShelter(Long shelter) {
        this.shelter = shelter;
    }

    public LocalDate getDayBroughtIn() {
        return dayBroughtIn;
    }

    public void setDayBroughtIn(LocalDate dayBroughtIn) {
        this.dayBroughtIn = dayBroughtIn;
    }

    public LocalDate getDateAdopted() {
        return dateAdopted;
    }

    public void setDateAdopted(LocalDate dateAdopted) {
        this.dateAdopted = dateAdopted;
    }

    public Set<TemplatedAnimalVolunteerDTO<Long>> getVolunteersAssigned(Set<VolunteerAnimal> volunteersAssigned) {
        Set<TemplatedAnimalVolunteerDTO<Long>> volunteers = new HashSet<>();
        for(VolunteerAnimal s : volunteersAssigned){
            volunteers.add(new TemplatedAnimalVolunteerDTO<Long>(s.getVolunteer().getId(), s.getAssignmentDay()));
        }

        return volunteers;
    }

    public Set<TemplatedAnimalVolunteerDTO<Long>> getVolunteersAssigned() {
        return volunteersAssigned;
    }

    public void setVolunteersAssigned(Set<TemplatedAnimalVolunteerDTO<Long>> volunteersAssigned) {
        this.volunteersAssigned = volunteersAssigned;
    }

    @Override
    public String toString() {
        return "AnimalIdDTO{" +
                "id=" + id +
                ", microchipNumber=" + microchipNumber +
                ", name='" + name + '\'' +
                ", medicalRecord=" + medicalRecord +
                ", shelter=" + shelter +
                ", dayBroughtIn=" + dayBroughtIn +
                ", dateAdopted=" + dateAdopted +
                ", volunteersAssigned=" + volunteersAssigned +
                '}';
    }
}
