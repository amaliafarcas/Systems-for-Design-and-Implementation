package a1.DTO.join;

import a1.Domain.Shelter;

public class ShelterDTO{
    private Long id;
    private String name;
    private String city;
    private Long postalCode;
    private Long phoneNumber;
    private int capacity;

    public ShelterDTO(){
    }

    public ShelterDTO(Shelter shelter) {
        this.id = shelter.getId();
        this.name = shelter.getName();
        this.city = shelter.getCity();
        this.postalCode = shelter.getPostalCode();
        this.phoneNumber = shelter.getPhoneNumber();
        this.capacity = shelter.getCapacity();
    }
    public ShelterDTO(Long id, String name, String city, Long postalCode, Long phoneNumber, int capacity) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
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
