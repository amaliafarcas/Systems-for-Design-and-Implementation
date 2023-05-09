package a1.DTO.join;

import java.time.LocalDate;

public class TemplatedAnimalVolunteerDTO <T> {
    private LocalDate assignmentDay;
    private T t;

    public TemplatedAnimalVolunteerDTO(T t, LocalDate assignmentDay) {
        this.assignmentDay = assignmentDay;
        this.t = t;
    }

    public LocalDate getAssignmentDay() {
        return assignmentDay;
    }

    public void setAssignmentDay(LocalDate assignmentDay) {
        this.assignmentDay = assignmentDay;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "TemplatedAnimalVolunteerDTO{" +
                "assignmentDay=" + assignmentDay +
                ", " + t +
                '}';
    }
}
