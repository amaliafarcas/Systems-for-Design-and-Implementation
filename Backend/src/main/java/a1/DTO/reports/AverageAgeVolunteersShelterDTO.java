package a1.DTO.reports;

import a1.Domain.Animal;
import a1.Domain.Shelter;
import a1.Domain.Volunteer;
import a1.Domain.VolunteerAnimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AverageAgeVolunteersShelterDTO{

    private Long id;
    private String shelter;
    private Double averageVolunteerAge;

    public AverageAgeVolunteersShelterDTO(Long id, String shelter, Double averageVolunteerAge) {
        this.id = id;
        this.shelter = shelter;
        this.averageVolunteerAge = averageVolunteerAge;
    }

    public AverageAgeVolunteersShelterDTO(Shelter shelter) {
        this.id = shelter.getId();
        this.shelter = shelter.getName();
        this.averageVolunteerAge = this.getAverageAge(shelter.getAnimalInShelters());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AverageAgeVolunteersShelterDTO that)) return false;
        return getId().equals(that.getId()) && getShelter().equals(that.getShelter()) && getAverageVolunteerAge().equals(that.getAverageVolunteerAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getShelter(), getAverageVolunteerAge());
    }

    public double getAverageAge(Set<Animal> animalInShelters){
        Set<Volunteer> volunteers = new HashSet<>();
        for(Animal a : animalInShelters){
            for(VolunteerAnimal v : a.getVolunteersAssigned()){
                volunteers.add(v.getVolunteer());
            }
        }

        double sum = volunteers.stream()
                .mapToInt(v -> Period.between(v.getDateOfBirth(), LocalDate.now()).getYears())
                .sum();
        return sum/volunteers.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShelter() {
        return shelter;
    }

    public void setShelter(String shelter) {
        this.shelter = shelter;
    }

    public Double getAverageVolunteerAge() {
        return averageVolunteerAge;
    }

    public void setAverageVolunteerAge(Double averageVolunteerAge) {
        this.averageVolunteerAge = averageVolunteerAge;
    }

    @Override
    public String toString() {
        return "AverageAgeVolunteersShelterDTO{" +
                "id=" + id +
                ", shelter='" + shelter + '\'' +
                ", averageVolunteerAge=" + averageVolunteerAge +
                '}';
    }
}
