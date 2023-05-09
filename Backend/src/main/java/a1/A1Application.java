package a1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class A1Application {

    @Autowired
    private DataSource dataSource;

    public static <Flyway> void main(String[] args) {
        SpringApplication.run(A1Application.class, args);

/*

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBC driver is available");

            // get the driver object
            Driver driver = DriverManager.getDriver("jdbc:postgresql://localhost/mydatabase");

            // get the major and minor version numbers
            int majorVersion = driver.getMajorVersion();
            int minorVersion = driver.getMinorVersion();

            System.out.println("PostgreSQL JDBC driver version: " + majorVersion + "." + minorVersion);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver is not available");
        } catch (SQLException e) {
            System.out.println("Failed to get JDBC driver version: " + e.getMessage());
        }
*/

    }
/*

    @PostConstruct
    public void insertRecords() throws InterruptedException {
        IAddRecords addVolunteerRecord = new AddVolunteerRecord(dataSource);
        Instant startVolunteer = Instant.now();
        Thread t1 = new Thread(() -> {
            addVolunteerRecord.addRecords();
            Instant endVolunteer = Instant.now();
            System.out.println("Inserted 1 million records into Volunteer table in " + Duration.between(startVolunteer, endVolunteer).toSeconds() + " seconds");
        });

        IAddRecords addMedicalRecord = new AddMedicalRecord(dataSource);
        Instant startMedicalRecord = Instant.now();
        Thread t2 = new Thread(() -> {
            addMedicalRecord.addRecords();
            Instant endMedicalRecord = Instant.now();
            System.out.println("Inserted 1 million records into Medical Record table in " + Duration.between(startMedicalRecord, endMedicalRecord).toSeconds() + " seconds");
        });

        IAddRecords addShelterRecord = new AddShelterRecord(dataSource);
        Instant startShelterRecord = Instant.now();
        Thread t3 = new Thread(() -> {
            addShelterRecord.addRecords();
            Instant endShelterRecord = Instant.now();
            System.out.println("Inserted 1 million records into Shelter table in " + Duration.between(startShelterRecord, endShelterRecord).toSeconds() + " seconds");
        });

        IAddRecords addAnimalRecord = new AddAnimalRecord(dataSource);
        Instant startAnimalRecord = Instant.now();
        Thread t4 = new Thread(() -> {
            addAnimalRecord.addRecords();
            Instant endAnimalRecord = Instant.now();
            System.out.println("Inserted 1 million records into Animal table in " + Duration.between(startAnimalRecord, endAnimalRecord).toSeconds() + " seconds");
        });

        IAddRecords addAnimalVolunteerRecord = new AddAnimalVolunteerRecord(dataSource);
        Instant startAnimalVolunteerRecord = Instant.now();
        Thread t5 = new Thread(() -> {
            addAnimalVolunteerRecord.addRecords();
            Instant endAnimalVolunteerRecord = Instant.now();
            System.out.println("Inserted 10 million records into Animal Volunteer table in " + Duration.between(startAnimalVolunteerRecord, endAnimalVolunteerRecord).toSeconds() + " seconds");
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        t4.start();
        t4.join();
        t5.start();
        t5.join();
    }
*/

}
