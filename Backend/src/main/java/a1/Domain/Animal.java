package a1.Domain;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Animal{

    @Id
    @SequenceGenerator(name = "animal_sequence",
            sequenceName = "animal_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_sequence")
    @Column(name = "animalId")
    private Long id;

    @Column(unique=true)
    @NotNull(message = "Microchip number is required")
   // @Size(min = 4, max=4, message="Microchip number must have 4 characters")
    private Long microchipNumber;

    @NotNull(message = "Name is required")
    //@Size(min = 3, max=10, message="Name must be between 3 and 10 characters")
    private String name;

    @OneToOne
    @JoinColumn(name = "medicalRecordId", unique=true)
    private MedicalRecord medicalRecordId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "shelterId", nullable = false)
    private Shelter shelterId;
    private LocalDate dayBroughtIn;
    private LocalDate dateAdopted;


    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<VolunteerAnimal> volunteersAssigned = new HashSet<>();


    public Animal(Long microchipNumber, String name, MedicalRecord medicalRecordId, Shelter shelterId, LocalDate dayBroughtIn, LocalDate dateAdopted) {
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.medicalRecordId = medicalRecordId;
        this.shelterId = shelterId;
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
    }


    public Animal(Long microchipNumber, String name, Shelter shelterId, LocalDate dayBroughtIn, LocalDate dateAdopted) {
        this.microchipNumber = microchipNumber;
        this.name = name;
        this.shelterId = shelterId;
        this.dayBroughtIn = dayBroughtIn;
        this.dateAdopted = dateAdopted;
    }

    public Animal() {
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

    public MedicalRecord getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(MedicalRecord medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Shelter getShelterId() {
        return shelterId;
    }

    public void setShelterId(Shelter shelterId) {
        this.shelterId = shelterId;
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

    public Set<VolunteerAnimal> getVolunteersAssigned() {
        return volunteersAssigned;
    }

    public void setVolunteersAssigned(Set<VolunteerAnimal> volunteersAssigned) {
        this.volunteersAssigned = volunteersAssigned;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", microchipNumber=" + microchipNumber +
                ", name='" + name + '\'' +
                ", medicalRecordId=" + medicalRecordId +
                ", shelterId=" + shelterId +
                ", dayBroughtIn=" + dayBroughtIn +
                ", dateAdopted=" + dateAdopted +
                '}';
    }

}
