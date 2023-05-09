package a1.DTO.update;

import java.time.LocalDate;

public class MedicalRecordReadDTO{
    private Long id;
    private LocalDate dateOfBirth;
    private Integer weight;
    private Integer vaccine;
    private Integer specialCare;
    private String additionalInformation;

    public MedicalRecordReadDTO(Long id, LocalDate dateOfBirth, Integer weight, Integer vaccine, Integer specialCare, String additionalInformation) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.vaccine = vaccine;
        this.specialCare = specialCare;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getVaccine() {
        return vaccine;
    }

    public void setVaccine(Integer vaccine) {
        this.vaccine = vaccine;
    }

    public Integer getSpecialCare() {
        return specialCare;
    }

    public void setSpecialCare(Integer specialCare) {
        this.specialCare = specialCare;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        return "MedicalRecordReadDTO{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", weight=" + weight +
                ", vaccine=" + vaccine +
                ", specialCare=" + specialCare +
                ", additionalInformation=" + additionalInformation +
                '}';
    }
}
