package a1.DTO.join;

import a1.Domain.Animal;
import a1.Domain.VolunteerAnimal;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AnimalForShelterDTO{
    private Long id;
    private Long microchipNumber;
    private String name;
    private Long medicalRecord;
    private LocalDate dayBroughtIn;
    private LocalDate dateAdopted;
    private Set<Long> volunteersAssigned;

    public AnimalForShelterDTO(Long id, Long microchipNumber, String name, Long medicalRecord, LocalDate dayBroughtIn, LocalDate dateAdopted, Set<Long> volunteersAssigned) {
        this.id = id;
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.medicalRecord = medicalRecord;
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
        this.volunteersAssigned = volunteersAssigned;
    }
    public AnimalForShelterDTO(Animal animal) {
        this.id = animal.getId();
        this.microchipNumber = animal.getMicrochipNumber();
        this.name = animal.getName();
        this.medicalRecord = animal.getMedicalRecordId().getId();
        this.dayBroughtIn = animal.getDayBroughtIn();
        this.dateAdopted = animal.getDateAdopted();
        this.volunteersAssigned = getVolunteersAssigned(animal.getVolunteersAssigned());

    }

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

    public Set<Long> getVolunteersAssigned() {
        return volunteersAssigned;
    }

    public void setVolunteersAssigned(Set<Long> volunteersAssigned) {
        this.volunteersAssigned = volunteersAssigned;
    }

    public Set<Long> getVolunteersAssigned(Set<VolunteerAnimal> volunteersAssigned) {
        Set<Long> volunteers = new HashSet<>();
        for(VolunteerAnimal s : volunteersAssigned){
            volunteers.add(s.getVolunteer().getId());
        }

        return volunteers;
    }

    @Override
    public String toString() {
        return "AnimalForShelterDTO{" +
                "id=" + id +
                ", microchipNumber=" + microchipNumber +
                ", name='" + name + '\'' +
                ", medicalRecord=" + medicalRecord +
                ", dayBroughtIn=" + dayBroughtIn +
                ", dateAdopted=" + dateAdopted +
                ", volunteersAssigned=" + volunteersAssigned +
                '}';
    }
}
