package a1.DTO.getIndividual;

import a1.DTO.join.MedicalRecordDTO;
import a1.DTO.join.ShelterDTO;
import a1.DTO.join.TemplatedAnimalVolunteerDTO;
import a1.DTO.join.VolunteerForAnimalsDTO;
import a1.Domain.Animal;
import a1.Domain.MedicalRecord;
import a1.Domain.Shelter;
import a1.Domain.VolunteerAnimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class AnimalDTO {
    private Long id;
    private Long microchipNumber;
    private String name;
    private MedicalRecordDTO medicalRecord;
    private ShelterDTO shelter;
    private LocalDate dayBroughtIn;
    private LocalDate dateAdopted;

    private Set<TemplatedAnimalVolunteerDTO<VolunteerForAnimalsDTO>> volunteersAssigned;

    public AnimalDTO() {
    }

    public AnimalDTO(Long id, Long microchipNumber, String name, MedicalRecord medicalRecord, Shelter shelter, LocalDate dayBroughtIn, LocalDate dateAdopted, Set<VolunteerAnimal> volunteersAssigned) {
        this.id = id;
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.medicalRecord = new MedicalRecordDTO(medicalRecord);
        this.shelter = new ShelterDTO(shelter);
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
        this.volunteersAssigned = getVolunteersAssigned(volunteersAssigned);
    }

    public AnimalDTO(Long microchipNumber, String name, MedicalRecordDTO medicalRecord, ShelterDTO shelter, LocalDate dayBroughtIn, LocalDate dateAdopted, Set<VolunteerAnimal> volunteersAssigned) {
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.medicalRecord = medicalRecord;
        this.shelter = shelter;
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
        this.volunteersAssigned = getVolunteersAssigned(volunteersAssigned);

    }

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.microchipNumber = animal.getMicrochipNumber();
        this.name = animal.getName();
        this.medicalRecord = new MedicalRecordDTO(animal.getMedicalRecordId());
        this.shelter = new ShelterDTO(animal.getShelterId());
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

    public MedicalRecordDTO getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecordDTO medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public ShelterDTO getShelter() {
        return shelter;
    }

    public void setShelter(ShelterDTO shelter) {
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

    public Set<TemplatedAnimalVolunteerDTO<VolunteerForAnimalsDTO>> getVolunteersAssigned(Set<VolunteerAnimal> volunteersAssigned) {
        Set<TemplatedAnimalVolunteerDTO<VolunteerForAnimalsDTO>> volunteers = new HashSet<>();
        for(VolunteerAnimal s : volunteersAssigned){
            volunteers.add(new TemplatedAnimalVolunteerDTO<VolunteerForAnimalsDTO>(new VolunteerForAnimalsDTO(s.getVolunteer()), s.getAssignmentDay()));
        }

        return volunteers;
    }

    public Set<TemplatedAnimalVolunteerDTO<VolunteerForAnimalsDTO>> getVolunteersAssigned() {
        return volunteersAssigned;
    }

    public void setVolunteersAssigned(Set<TemplatedAnimalVolunteerDTO<VolunteerForAnimalsDTO>> volunteersAssigned) {
        this.volunteersAssigned = volunteersAssigned;
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
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