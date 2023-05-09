package a1.DTO.join;

import a1.Domain.Volunteer;

import java.time.LocalDate;

public class VolunteerForAnimalsDTO {

    private Long id;
    private String name;
    private Long code;
    private LocalDate dateOfBirth;
    private String email;
    private boolean isSpecialist;

    public VolunteerForAnimalsDTO(Long id, String name, Long code, LocalDate dateOfBirth, String email, boolean isSpecialist) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isSpecialist = isSpecialist;
    }

    public VolunteerForAnimalsDTO(Volunteer volunteer) {
        this.id = volunteer.getId();
        this.name = volunteer.getName();
        this.code = volunteer.getCode();
        this.dateOfBirth = volunteer.getDateOfBirth();
        this.email = volunteer.getEmail();
        this.isSpecialist = volunteer.isSpecialist();
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

    @Override
    public String toString() {
        return "VolunteerForAnimalsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", isSpecialist=" + isSpecialist +
                '}';
    }
}
