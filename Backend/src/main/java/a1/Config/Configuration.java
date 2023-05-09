/*
package a1.Config;

import a1.Domain.*;
import a1.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Transactional
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    CommandLineRunner commandLineRunner(AnimalRepository animalRepository, ShelterRepository shelterRepository, MedicalRecordRepository medicalRecordRepository, VolunteerRepository volunteerRepository, VolunteerAnimalRepository volunteerAnimalRepository){
        return args -> {

            Shelter cluj = new Shelter("Cluj Animals", "Cluj", 758964L, 40758968566L, 450);
            Shelter oradea = new Shelter("Oradea Animals", "Oradea", 658458L, 40388968519L, 300);
            Shelter zalau = new Shelter("Zalau Animals", "Zalau", 112244L, 40388968511L, 50);
            Shelter timisoara = new Shelter("Timisoara Animals", "Timisoara", 112233L, 40399968592L, 370);
            Shelter bucuresti = new Shelter("Bucuresti Animals", "Bucuresti", 658450L, 40388558593L, 450);
            Shelter pitesti = new Shelter("Pitesti Animals", "Pitesti", 651458L, 40380968594L, 200);
            Shelter satuMare = new Shelter("SatuMare Animals", "Satu Mare", 658478L, 40388111575L, 100);
            Shelter sibiu = new Shelter("Sibiu Animals", "Sibiu", 158458L, 40388911199L, 120);
            Shelter brasov = new Shelter("Brasov Animals", "Brasov", 602458L, 40388900199L, 400);
            Shelter iasi = new Shelter("Iasi Animals", "Iasi", 651018L, 40388101799L, 90);


            shelterRepository.saveAll(List.of(cluj, oradea, zalau, timisoara, bucuresti, pitesti, satuMare, sibiu, brasov, iasi));

            MedicalRecord zeusRecord = new MedicalRecord(LocalDate.of(2019, Month.JULY, 22), 15, true, false);
            MedicalRecord heraRecord = new MedicalRecord(LocalDate.of(2020, Month.JULY, 22), 8, false, false);
            MedicalRecord cooperRecord = new MedicalRecord(LocalDate.of(2019, Month.MAY, 10), 45, true, false);
            MedicalRecord husRecord = new MedicalRecord(LocalDate.of(2017, Month.OCTOBER, 6), 2, false, false);
            MedicalRecord addaxRecord = new MedicalRecord(LocalDate.of(2010, Month.AUGUST, 7), 30, true, true);
            MedicalRecord beeRecord = new MedicalRecord(LocalDate.of(2021, Month.JANUARY, 2), 24, false, false);
            MedicalRecord blueJayRecord = new MedicalRecord(LocalDate.of(2019, Month.SEPTEMBER, 21), 25, false, false);
            MedicalRecord collieRecord = new MedicalRecord(LocalDate.of(2020, Month.FEBRUARY, 17), 39, false, true);
            MedicalRecord dreverRecord = new MedicalRecord(LocalDate.of(2018, Month.APRIL, 22), 10, true, false);
            MedicalRecord fivRecord = new MedicalRecord(LocalDate.of(2020, Month.JULY, 22), 31, false, false);

            medicalRecordRepository.saveAll(List.of(zeusRecord, heraRecord, cooperRecord, husRecord, addaxRecord, beeRecord, blueJayRecord, collieRecord, dreverRecord, fivRecord));


            Animal zeus = new Animal(5897L, "Zeus", zeusRecord, cluj, LocalDate.of(2020, Month.JULY, 22), null);
            Animal hera = new Animal(5687L, "Hera", heraRecord, cluj, LocalDate.of(2020, Month.JULY, 22), null);
            Animal cooper = new Animal(5007L, "Cooper", cooperRecord, zalau, LocalDate.of(2020, Month.JULY, 22), LocalDate.of(2022, Month.JULY, 22));
            Animal hus = new Animal(1287L, "Hus", husRecord, bucuresti, LocalDate.of(2010, Month.JULY, 22), null);
            Animal addax = new Animal(1897L, "Addax", addaxRecord, brasov, LocalDate.of(2020, Month.JULY, 22), LocalDate.of(2022, Month.JULY, 22));
            Animal bee = new Animal(3687L, "Bee", beeRecord, bucuresti, LocalDate.of(2020, Month.JULY, 22), null);
            Animal blueJay = new Animal(5967L, "Blue Jay", blueJayRecord, pitesti, LocalDate.of(2020, Month.MARCH, 22), null);
            Animal collie = new Animal(9987L, "Collie", collieRecord, satuMare, LocalDate.of(2019, Month.JULY, 22), null);
            Animal drever = new Animal(5890L, "Drever", dreverRecord, zalau, LocalDate.of(2020, Month.JULY, 22), LocalDate.of(2021, Month.JULY, 22));
            Animal fiv = new Animal(1235L, "Fiv", fivRecord, oradea, LocalDate.of(2020, Month.JULY, 22), null);
            animalRepository.saveAll(List.of(zeus, hera, cooper, hus, addax, bee, blueJay, collie, drever, fiv));


            Volunteer ama = new Volunteer("Ama", 365L, LocalDate.of(2000, Month.JULY, 22), "ama@gmail.com", false);
            Volunteer ale = new Volunteer("Ale", 689L, LocalDate.of(1989, Month.JANUARY, 7), "ale@gmail.com", true);
            Volunteer vlad = new Volunteer("Vlad", 115L, LocalDate.of(2001, Month.APRIL, 10), "vlad@gmail.com", false);
            Volunteer cristi = new Volunteer("Cristi", 600L, LocalDate.of(1999, Month.SEPTEMBER, 4), "cristi@gmail.com", true);
            Volunteer mara = new Volunteer("Mara", 365L, LocalDate.of(2000, Month.NOVEMBER, 14), "mara@gmail.com", false);
            Volunteer sorana = new Volunteer("Sorana", 779L, LocalDate.of(1998, Month.DECEMBER, 30), "sorana@gmail.com", false);
            Volunteer rares = new Volunteer("Rares", 399L, LocalDate.of(2004, Month.MAY, 1), "rares@gmail.com", false);
            Volunteer iulia = new Volunteer("Iulia", 689L, LocalDate.of(1990, Month.NOVEMBER, 18), "iulia@gmail.com", true);
            Volunteer albert = new Volunteer("Albert", 124L, LocalDate.of(2001, Month.JULY, 6), "albert@gmail.com", false);
            Volunteer adi = new Volunteer("Adi", 578L, LocalDate.of(1989, Month.AUGUST, 29), "adi@gmail.com", true);

            volunteerRepository.saveAll(List.of(ama, ale, iulia, rares, mara, adi, albert, sorana, vlad, cristi));

            VolunteerAnimal amaZeus = new VolunteerAnimal(zeus, ama, LocalDate.of(2023, Month.MARCH, 2));
            VolunteerAnimal aleZeus = new VolunteerAnimal(zeus, ale, LocalDate.of(2023, Month.JANUARY, 12));
            VolunteerAnimal aleHera = new VolunteerAnimal(hera, ale, LocalDate.of(2023, Month.FEBRUARY, 24));
            VolunteerAnimal vladHus = new VolunteerAnimal(hus, vlad, LocalDate.of(2022, Month.MARCH, 14));
            VolunteerAnimal iuliaHus = new VolunteerAnimal(hus, iulia, LocalDate.of(2023, Month.FEBRUARY, 1));
            VolunteerAnimal amaBee = new VolunteerAnimal(bee, ama, LocalDate.of(2023, Month.FEBRUARY, 19));
            VolunteerAnimal albertDrever = new VolunteerAnimal(drever, albert, LocalDate.of(2023, Month.JANUARY, 30));
            VolunteerAnimal raresDrever = new VolunteerAnimal(drever, rares, LocalDate.of(2023, Month.JANUARY, 17));
            VolunteerAnimal maraFiv = new VolunteerAnimal(fiv, mara, LocalDate.of(2023, Month.MARCH, 6));
            VolunteerAnimal soranaBlueJay = new VolunteerAnimal(blueJay, sorana, LocalDate.of(2023, Month.FEBRUARY, 7));


            volunteerAnimalRepository.saveAll(List.of(amaZeus, aleZeus,aleHera, vladHus, iuliaHus, amaBee, albertDrever, raresDrever, maraFiv, soranaBlueJay));
        };
    }
}
*/
