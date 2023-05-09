package a1.DTO.join;

import a1.Domain.MedicalRecord;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MedicalRecordDTO{

    private Long id;
    private LocalDate dateOfBirth;
    private Integer age;
    private Integer weight;
    private boolean vaccine;
    private boolean specialCare;
    private String additionalInformation;
    

    public MedicalRecordDTO() {}

    public MedicalRecordDTO(LocalDate dateOfBirth, Integer weight, boolean vaccine, boolean specialCare, String additionalInformation) {
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
        this.additionalInformation = additionalInformation;
    }
    public MedicalRecordDTO(Long id, LocalDate dateOfBirth, Integer age, Integer weight, boolean vaccine, boolean specialCare, String additionalInformation) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
        this.additionalInformation = additionalInformation;
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        this.id = medicalRecord.getId();
        this.dateOfBirth = medicalRecord.getDateOfBirth();
        this.age = medicalRecord.getAge();
        this.weight = medicalRecord.getWeight();
        this.vaccine = medicalRecord.isVaccine();
        this.specialCare = medicalRecord.isSpecialCare();
        this.additionalInformation = medicalRecord.getAdditionalInformation();
    }

    @JsonCreator
    public static MedicalRecordDTO fromJson(
            @JsonProperty("id") Long id,
            @JsonProperty("dateOfBirth") LocalDate dateOfBirth,
            @JsonProperty("age") Integer age,
            @JsonProperty("weight") Integer weight,
            @JsonProperty("vaccine") boolean vaccine,
            @JsonProperty("specialCare") boolean specialCare,
            @JsonProperty("additionalInformation") String additionalInformation) {
        return new MedicalRecordDTO(id, dateOfBirth, age, weight, vaccine, specialCare, additionalInformation);
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
        return age;
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
        return "MedicalRecordDTO{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", weight=" + weight +
                ", vaccine=" + vaccine +
                ", specialCare=" + specialCare +
                ", additionalInformation =" + additionalInformation +
                '}';
    }
}
