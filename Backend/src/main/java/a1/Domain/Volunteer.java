package a1.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Volunteer {
    @Id
    @SequenceGenerator(name = "volunteer_sequence",
            sequenceName = "volunteer_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "volunteer_sequence")
    @Column(name = "volunteerId")
    private Long id;
    private String name;
    private Long code;
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String email;
    private boolean isSpecialist;

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<VolunteerAnimal> animalsAssigned = new HashSet<>();

    public Volunteer(String name, Long code, LocalDate dateOfBirth, String email, boolean isSpecialist) {
        this.name = name;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isSpecialist = isSpecialist;
    }


    public Volunteer() {

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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSpecialist() {
        return isSpecialist;
    }

    public void setSpecialist(boolean specialist) {
        isSpecialist = specialist;
    }

    public Set<VolunteerAnimal> getAnimalsAssigned() {
        return animalsAssigned;
    }

    public void setAnimalsAssigned(Set<VolunteerAnimal> volunteerAnimals) {
        this.animalsAssigned = volunteerAnimals;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", isSpecialist=" + isSpecialist +
                '}';
    }
}
