package a1.DTO.getIndividual;

import a1.DTO.getAll.AnimalIdDTO;
import a1.DTO.join.AnimalForShelterDTO;
import a1.Domain.Animal;
import a1.Domain.Shelter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShelterListDTO {
    private Long id;
    private String name;
    private String city;
    private Long postalCode;
    private Long phoneNumber;
    private int capacity;
    private Set<AnimalForShelterDTO> animals;

    public ShelterListDTO(Shelter shelter) {
        this.id = shelter.getId();
        this.name = shelter.getName();
        this.city = shelter.getCity();
        this.postalCode = shelter.getPostalCode();
        this.phoneNumber = shelter.getPhoneNumber();
        this.capacity = shelter.getCapacity();
        this.animals = getAnimalsInShelter(shelter.getAnimalInShelters());
    }
    public ShelterListDTO(Long id, String name, String city, Long postalCode, Long phoneNumber, int capacity, Set<Animal> animals) {
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

    public Set<AnimalForShelterDTO> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<AnimalForShelterDTO> animals) {
        this.animals = animals;
    }

    public Set<AnimalForShelterDTO> getAnimalsInShelter(Set<Animal> animals) {
        Set<AnimalForShelterDTO> animalsInShelter = new HashSet<>();
        for(Animal s : animals){
            animalsInShelter.add(new AnimalForShelterDTO(s));
        }

        return animalsInShelter;
    }

    @Override
    public String toString() {
        return "ShelterDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", phoneNumber=" + phoneNumber +
                ", capacity=" + capacity +
                '}';
    }
}
