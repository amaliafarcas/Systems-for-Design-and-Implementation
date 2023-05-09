package a1.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public
class VolunteerAnimalPrimaryKey implements Serializable {

   /* @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "animalId", nullable = false)*/
    //@Column(name = "animalIdPK")
    Long animalId;

    /*@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteerId", nullable = false)*/
    //@Column(name = "volunteerId")
    Long volunteerId;

    /*//@JsonProperty("justAnimalId")
    public String getIdAnimal() {
        return animalId.toString();
    }

    //@JsonProperty("justVolunteerId")
    public String getIdVolunteer() {
        return volunteerId.toString();
    }*/

    public VolunteerAnimalPrimaryKey(Long animalId, Long volunteerId) {
        this.animalId = animalId;
        this.volunteerId = volunteerId;
    }

    public VolunteerAnimalPrimaryKey() {
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        VolunteerAnimalPrimaryKey other = (VolunteerAnimalPrimaryKey) obj;
        return Objects.equals(animalId, other.animalId) &&
                Objects.equals(volunteerId, other.volunteerId);
    }
}
