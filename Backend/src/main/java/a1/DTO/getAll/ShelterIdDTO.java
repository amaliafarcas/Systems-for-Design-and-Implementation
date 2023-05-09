package a1.DTO.getAll;

import a1.Domain.Animal;
import a1.Domain.Shelter;
import a1.Domain.VolunteerAnimal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ShelterIdDTO {

    private Long id;
    private String name;
    private String city;
    private Long postalCode;
    private Long phoneNumber;
    private int capacity;
    private Set<Long> animals;

    public ShelterIdDTO(Shelter shelter) {
        this.id = shelter.getId();
        this.name = shelter.getName();
        this.city = shelter.getCity();
        this.postalCode = shelter.getPostalCode();
        this.phoneNumber = shelter.getPhoneNumber();
        this.capacity = shelter.getCapacity();
        this.animals = getAnimalsInShelter(shelter.getAnimalInShelters());
    }
    public ShelterIdDTO(Long id, String name, String city, Long postalCode, Long phoneNumber, int capacity, Set<Animal> animals) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.animals = getAnimalsInShelter(animals);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Long> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Long> animals) {
        this.animals = animals;
    }

    public Set<Long> getAnimalsInShelter(Set<Animal> animals) {
        Set<Long> animalsInShelter = new HashSet<>();
        for(Animal s : animals){
            animalsInShelter.add(s.getId());
        }

        return animalsInShelter;
    }

    @Override
    public String toString() {
        return "ShelterIdDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", phoneNumber=" + phoneNumber +
                ", capacity=" + capacity +
                ", animals=" + animals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShelterIdDTO that)) return false;
        return getCapacity() == that.getCapacity() && Objects.equals(getId(), that.getId()) && getName().equals(that.getName()) && getCity().equals(that.getCity()) && getPostalCode().equals(that.getPostalCode()) && getPhoneNumber().equals(that.getPhoneNumber()) && Objects.equals(getAnimals(), that.getAnimals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCity(), getPostalCode(), getPhoneNumber(), getCapacity(), getAnimals());
    }
}
