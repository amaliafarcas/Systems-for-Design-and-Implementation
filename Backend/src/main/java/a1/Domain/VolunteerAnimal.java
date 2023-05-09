package a1.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "VolunteerAnimal")
public class VolunteerAnimal {

    @EmbeddedId
    private VolunteerAnimalPrimaryKey primaryKey;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "animalId", nullable = false, insertable=false, updatable=false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Animal animal;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteerId", nullable = false, insertable=false, updatable=false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Volunteer volunteer;


    private LocalDate assignmentDay;

   /* @JsonProperty("justAnimalId")
    public String getIdAnimal() {
        return "id:" + animal.getId().toString() + ", assignmentDay: " + assignmentDay;
    }


   @JsonProperty("justVolunteerId")
    public String getIdVolunteer() {
        return "id: " + volunteer.getId().toString()+ ", assignmentDay: " + assignmentDay;
    }*/


    public VolunteerAnimal(VolunteerAnimalPrimaryKey primaryKey, Animal animal, Volunteer volunteer, LocalDate assignmentDay) {
        this.primaryKey = primaryKey;
        this.animal = animal;
        this.volunteer = volunteer;
        this.assignmentDay = assignmentDay;
    }

    public VolunteerAnimal(Animal animal, Volunteer volunteer, LocalDate assignmentDay) {
        this.primaryKey = new VolunteerAnimalPrimaryKey(animal.getId(), volunteer.getId());
        this.animal = animal;
        this.volunteer = volunteer;
        this.assignmentDay = assignmentDay;
    }

    public VolunteerAnimal() {
    }

    public VolunteerAnimalPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(VolunteerAnimalPrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public LocalDate getAssignmentDay() {
        return assignmentDay;
    }

    public void setAssignmentDay(LocalDate assignmentDay) {
        this.assignmentDay = assignmentDay;
    }

    @Override
    public String toString() {
        return "VolunteerAnimal{" +
                "animal=" + animal +
                ", volunteer=" + volunteer +
                ", assignmentDay=" + assignmentDay +
                '}';
    }
}
