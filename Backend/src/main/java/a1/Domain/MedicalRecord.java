package a1.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class MedicalRecord {

    @Id
    @SequenceGenerator(name = "medical_record_sequence",
            sequenceName = "medical_record_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_record_sequence")
    @Column(name = "medicalRecordId")
    private Long id;
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;
    private Integer weight;
    private boolean vaccine;
    private boolean specialCare;
    private String additionalInformation;

    public MedicalRecord() {
    }

    public MedicalRecord(LocalDate dateOfBirth, Integer weight, boolean vaccine, boolean specialCare) {
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
    }
    public MedicalRecord(LocalDate dateOfBirth, Integer weight, boolean vaccine, boolean specialCare, String additionalInformation) {
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
        this.additionalInformation = additionalInformation;
    }

    public MedicalRecord(LocalDate dateOfBirth, Integer age, Integer weight, boolean vaccine, boolean specialCare, String additionalInformation) {
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
        this.additionalInformation = additionalInformation;

    }

    public MedicalRecord(Long id, LocalDate dateOfBirth, Integer weight, boolean vaccine, boolean specialCare, String additionalInformation) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
        this.additionalInformation = additionalInformation;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public boolean isSpecialCare() {
        return specialCare;
    }

    public void setSpecialCare(boolean specialCare) {
        this.specialCare = specialCare;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", weight=" + weight +
                ", vaccine=" + vaccine +
                ", specialCare=" + specialCare +
                ", additionalInformation=" + additionalInformation +
                '}';
    }
}
