package a1.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Shelter{

    @Id
    @SequenceGenerator(name = "shelter_sequence",
            sequenceName = "shelter_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shelter_sequence")
    @Column(name = "shelterId")
    private Long id;
    private String name;
    private String city;
    @Column(unique=true)
    private Long postalCode;
    @Column(unique=true)
    private Long phoneNumber;
    private int capacity;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "shelterId")
   /* @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)*/
    private Set<Animal> animalInShelters = new HashSet<>();

    public Shelter(){
    }
    public Shelter(String name, String city, Long postalCode, Long phoneNumber, int capacity) {
        this.name = name;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        //this.animalInShelters = new ArrayList<>();
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

    public Set<Animal> getAnimalInShelters() {
        return animalInShelters;
    }

    public void setAnimalInShelters(Set<Animal> animalInShelters) {
        this.animalInShelters = animalInShelters;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", phoneNumber=" + phoneNumber +
                ", capacity=" + capacity +
                '}';
    }
}
