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

public class VaccinatedAnimalsRatioSheltersDTO{

    private Long id;
    private String shelter;
    private Double ratioVaccinated;

    public VaccinatedAnimalsRatioSheltersDTO(Long id, String shelter, Double ratioVaccinated) {
        this.id = id;
        this.shelter = shelter;
        this.ratioVaccinated = ratioVaccinated;
    }

    public VaccinatedAnimalsRatioSheltersDTO(Shelter shelter) {
        this.id = shelter.getId();
        this.shelter = shelter.getName();
        this.ratioVaccinated = this.getAverageAge(shelter.getAnimalInShelters());
    }

    public double getAverageAge(Set<Animal> animalInShelters){
        double vaccinated=0;
        for(Animal a : animalInShelters){
            if(a.getMedicalRecordId().isVaccine()){
                vaccinated++;
            }
        }

        return vaccinated/animalInShelters.size();
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

    public Double getRatioVaccinated() {
        return ratioVaccinated;
    }

    public void setRatioVaccinated(Double ratioVaccinated) {
        this.ratioVaccinated = ratioVaccinated;
    }


    @Override
    public String toString() {
        return "VaccinatedAnimalsRatioSheltersDTO{" +
                "id=" + id +
                ", shelter='" + shelter + '\'' +
                ", ratioVaccinated=" + ratioVaccinated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VaccinatedAnimalsRatioSheltersDTO that)) return false;
        return getId().equals(that.getId()) && getShelter().equals(that.getShelter()) && getRatioVaccinated().equals(that.getRatioVaccinated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getShelter(), getRatioVaccinated());
    }
}
