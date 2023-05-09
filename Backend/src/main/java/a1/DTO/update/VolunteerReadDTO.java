package a1.DTO.update;

import java.time.LocalDate;

public class VolunteerReadDTO{

    private Long id;
    private String name;
    private Long code;
    private LocalDate dateOfBirth;
    private String email;
    private Integer isSpecialist;

    public VolunteerReadDTO(Long id, String name, Long code, LocalDate dateOfBirth, String email, Integer isSpecialist) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isSpecialist = isSpecialist;
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

    public Integer getIsSpecialist() {
        return isSpecialist;
    }

    public void setIsSpecialist(Integer isSpecialist) {
        this.isSpecialist = isSpecialist;
    }

    @Override
    public String toString() {
        return "VolunteerReadDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", isSpecialist=" + isSpecialist +
                '}';
    }
}
