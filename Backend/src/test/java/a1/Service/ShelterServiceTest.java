package a1.Service;

import a1.DTO.getAll.ShelterIdDTO;
import a1.DTO.reports.AverageAgeVolunteersShelterDTO;
import a1.DTO.reports.VaccinatedAnimalsRatioSheltersDTO;
import a1.Domain.*;
import a1.Repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShelterServiceTest {

    @Mock
    private ShelterRepository shelterRepository;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private VolunteerRepository volunteerRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private VolunteerAnimalRepository volunteerAnimalRepository;

    @InjectMocks
    private ShelterService shelterService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetSheltersGreaterCapacity() {
        // Arrange
        int capacity = 150;
        List<Shelter> shelters = new ArrayList<>();
        shelters.add(new Shelter("ClujAnimals", "Cluj", 758964L, 40758968566L, 450));
        shelters.add(new Shelter("ZalauAnimals", "Zalau", 112244L, 40388968511L, 50));
        shelters.add(new Shelter("PitestiAnimals", "Pitesti", 651458L, 40380968594L, 200));

        List<ShelterIdDTO> expected = new ArrayList<>();
        expected.add(new ShelterIdDTO(shelters.get(0)));
        expected.add(new ShelterIdDTO(shelters.get(2)));

        when(shelterRepository.findAll()).thenReturn(shelters);

        // Act
        List<ShelterIdDTO> actual = shelterService.getSheltersGreaterCapacity(capacity);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void exportReportVolunteer() {
        List<Shelter> shelters = new ArrayList<>();
        Shelter cluj = new Shelter("ClujAnimals", "Cluj", 758964L, 40758968566L, 450);
        cluj.setId(1L);
        shelters.add(cluj);
        shelterRepository.saveAll(List.of(cluj));


        List<Animal> animals = new ArrayList<>();
        Animal zeus = new Animal(5897L, "Zeus", null, cluj, LocalDate.of(2020, Month.JULY, 22), null);
        Animal hera = new Animal(5687L, "Hera", null, cluj, LocalDate.of(2020, Month.JULY, 22), null);
        Animal cooper = new Animal(5007L, "Cooper", null, cluj, LocalDate.of(2020, Month.JULY, 22), LocalDate.of(2022, Month.JULY, 22));
        animals.add(zeus);
        animals.add(hera);
        animals.add(cooper);
        animalRepository.saveAll(List.of(zeus, hera, cooper));

        List<Volunteer> volunteers = new ArrayList<>();
        Volunteer ama = new Volunteer("Ama", 365L, LocalDate.of(2000, Month.JULY, 22), "ama@gmail.com", false);
        Volunteer ale = new Volunteer("Ale", 689L, LocalDate.of(1989, Month.JANUARY, 7), "ale@gmail.com", true);
        Volunteer vlad = new Volunteer("Vlad", 115L, LocalDate.of(2001, Month.APRIL, 10), "vlad@gmail.com", false);
        volunteers.add(ama);
        volunteers.add(ale);
        volunteers.add(vlad);
        volunteerRepository.saveAll(List.of(ama, ale, vlad));

        List<VolunteerAnimal> volunteerAnimal = new ArrayList<>();
        VolunteerAnimal amaZeus = new VolunteerAnimal(zeus, ama, LocalDate.of(2023, Month.MARCH, 2));
        VolunteerAnimal aleZeus = new VolunteerAnimal(zeus, ale, LocalDate.of(2023, Month.JANUARY, 12));
        VolunteerAnimal aleHera = new VolunteerAnimal(hera, ale, LocalDate.of(2023, Month.FEBRUARY, 24));
        VolunteerAnimal vladCooper = new VolunteerAnimal(cooper, vlad, LocalDate.of(2022, Month.MARCH, 14));
        volunteerAnimal.add(amaZeus);
        volunteerAnimal.add(aleHera);
        volunteerAnimal.add(aleZeus);
        volunteerAnimal.add(vladCooper);
        volunteerAnimalRepository.saveAll(List.of(amaZeus, aleZeus,aleHera, vladCooper));


        cluj.setAnimalInShelters(Set.of(zeus, hera, cooper));
        shelterRepository.save(cluj);

        zeus.setVolunteersAssigned(Set.of(amaZeus, aleZeus));
        hera.setVolunteersAssigned(Set.of(aleHera));
        cooper.setVolunteersAssigned(Set.of(vladCooper));
        animalRepository.saveAll(List.of(zeus, hera, cooper));

        List<AverageAgeVolunteersShelterDTO> expected = new ArrayList<>();
        expected.add(new AverageAgeVolunteersShelterDTO(1L, "ClujAnimals", 25.666666666666668));

        when(shelterRepository.findAll()).thenReturn(shelters);
        when(volunteerRepository.findAll()).thenReturn(volunteers);
        when(animalRepository.findAll()).thenReturn(animals);
        when(volunteerAnimalRepository.findAll()).thenReturn(volunteerAnimal);

        // Act
        List<AverageAgeVolunteersShelterDTO> actual = shelterService.exportReportVolunteer2();

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void exportReportAnimal() {
        List<Shelter> shelters = new ArrayList<>();
        Shelter cluj = new Shelter("ClujAnimals", "Cluj", 758964L, 40758968566L, 450);
        cluj.setId(1L);
        shelters.add(cluj);
        shelterRepository.saveAll(List.of(cluj));

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord zeusRecord = new MedicalRecord(LocalDate.of(2019, Month.JULY, 22), 15, true, false);
        MedicalRecord heraRecord = new MedicalRecord(LocalDate.of(2020, Month.JULY, 22), 8, false, false);
        MedicalRecord cooperRecord = new MedicalRecord(LocalDate.of(2019, Month.MAY, 10), 45, true, false);
        medicalRecords.add(zeusRecord);
        medicalRecords.add(heraRecord);
        medicalRecords.add(cooperRecord);
        medicalRecordRepository.saveAll(List.of(zeusRecord, heraRecord, cooperRecord));

        List<Animal> animals = new ArrayList<>();
        Animal zeus = new Animal(5897L, "Zeus", zeusRecord, cluj, LocalDate.of(2020, Month.JULY, 22), null);
        Animal hera = new Animal(5687L, "Hera", heraRecord, cluj, LocalDate.of(2020, Month.JULY, 22), null);
        Animal cooper = new Animal(5007L, "Cooper", cooperRecord, cluj, LocalDate.of(2020, Month.JULY, 22), LocalDate.of(2022, Month.JULY, 22));
        animals.add(zeus);
        animals.add(hera);
        animals.add(cooper);
        animalRepository.saveAll(List.of(zeus, hera, cooper));


        cluj.setAnimalInShelters(Set.of(zeus, hera, cooper));
        shelterRepository.save(cluj);


        List<VaccinatedAnimalsRatioSheltersDTO> expected = new ArrayList<>();
        expected.add(new VaccinatedAnimalsRatioSheltersDTO(1L, "ClujAnimals", 0.6666666666666666));

        when(shelterRepository.findAll()).thenReturn(shelters);
        when(animalRepository.findAll()).thenReturn(animals);

        // Act
        List<VaccinatedAnimalsRatioSheltersDTO> actual = shelterService.exportReportAnimal();

        // Assert
        assertEquals(expected, actual);

    }


}