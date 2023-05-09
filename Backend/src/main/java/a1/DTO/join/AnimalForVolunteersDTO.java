package a1.DTO.join;

import a1.Domain.Animal;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AnimalForVolunteersDTO{
    private Long id;

    @NotNull(message = "Microchip number is required")
    @Size(min = 4, max=4, message="Microchip number must have 4 characters")
    private Long microchipNumber;

    @NotNull(message = "Name is required")
    @Size(min = 3, max=10, message="Name must be between 3 and 10 characters")
    private String name;

    @NotNull(message = "Medical Record id must not be null")
    private Long medicalRecord;

    @NotNull(message = "Shelter id must not be null")
    private Long shelter;

    @NotNull(message = "Day brought in must not be null")
    @PastOrPresent(message = "The day brought in must be in the past or the present")
    private LocalDate dayBroughtIn;

    @PastOrPresent(message = "The adoption day must be in the past or the present")
    private LocalDate dateAdopted;

   /* @AssertTrue(message = "The adoption date must be after the day brought in")
    private boolean isDateAdoptedValid() {
        return dateAdopted == null || dateAdopted.isAfter(dayBroughtIn);
    }*/

    public AnimalForVolunteersDTO(Long id, Long microchipNumber, String name, Long medicalRecord, Long shelter, LocalDate dayBroughtIn, LocalDate dateAdopted) {
        this.id = id;
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.medicalRecord = medicalRecord;
        this.shelter = shelter;
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
    }

    public AnimalForVolunteersDTO(Long microchipNumber, String name, Long medicalRecord, Long shelter, LocalDate dayBroughtIn, LocalDate dateAdopted) {
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.medicalRecord = medicalRecord;
        this.shelter = shelter;
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
    }

    public AnimalForVolunteersDTO(Animal animal) {
        this.id = animal.getId();
        this.microchipNumber = animal.getMicrochipNumber();
        this.name = animal.getName();
        this.medicalRecord = animal.getMedicalRecordId().getId();
        this.shelter = animal.getShelterId().getId();
        this.dayBroughtIn = animal.getDayBroughtIn();
        this.dateAdopted = animal.getDateAdopted();
    }

    public AnimalForVolunteersDTO() {
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

    @Override
    public String toString() {
        return "AnimalForVolunteersDTO{" +
                "id=" + id +
                ", microchipNumber=" + microchipNumber +
                ", name='" + name + '\'' +
                ", medicalRecord=" + medicalRecord +
                ", shelter=" + shelter +
                ", dayBroughtIn=" + dayBroughtIn +
                ", dateAdopted=" + dateAdopted +
                '}';
    }
}
