package a1.AddRecords;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Component
public class AddAnimalRecord implements IAddRecords {

    private final DataSource dataSource;

    public AddAnimalRecord(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addRecords() {
        int batchSize = 1000;
        int numRecords = 1000000;

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            // Drop constraints
            PreparedStatement dropStmt = conn.prepareStatement("ALTER TABLE animal DISABLE TRIGGER ALL;");
            dropStmt.executeUpdate();

            // Retrieve existing microchip numbers
            PreparedStatement selectStmt = conn.prepareStatement("SELECT microchip_number FROM animal");
            ResultSet rs = selectStmt.executeQuery();
            Set<Long> existing = new HashSet<>();
            while (rs.next()) {
                existing.add(rs.getLong("microchip_number"));
            }

            // Insert data
            Faker faker = new Faker();
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO animal (animal_id, date_adopted, day_brought_in, microchip_number, name, medical_record_id, shelter_id) VALUES (nextval('animal_sequence'), ?, ?, ?, ?, ?, ?)");

            int recordId = 11;

            for (int i = 1; i <= numRecords; i++) {

                Long microchipNumber = faker.number().randomNumber(9, true);
                while (existing.contains(microchipNumber)) {
                    microchipNumber = faker.number().randomNumber(9, true);
                }
                existing.add(microchipNumber);

                insertStmt.setLong(3, microchipNumber);
                insertStmt.setString(4, faker.name().firstName());
                insertStmt.setLong(5, recordId);
                insertStmt.setInt(6, faker.number().numberBetween(1, 1000000));

                PreparedStatement selectBirthStmt = conn.prepareStatement("SELECT date_of_birth FROM medical_record WHERE medical_record_id = ?");
                selectBirthStmt.setLong(1, recordId);
                ResultSet rsBirth = selectBirthStmt.executeQuery();
                if(rsBirth.next()) {
                    LocalDate dateOfBirth = rsBirth.getDate("date_of_birth").toLocalDate();
                    // Calculate the number of days between the start and end dates
                    long daysBetween = ChronoUnit.DAYS.between(dateOfBirth, LocalDate.now());

                    // Generate a random number of days between 0 and daysBetween
                    long randomDays = (long) (Math.random() * daysBetween);

                    // Add the random number of days to the start date to get the random date
                    LocalDate dateBroughtIn = dateOfBirth.plusDays(randomDays);

                    insertStmt.setDate(2, Date.valueOf(dateBroughtIn));

                    insertStmt.setObject(1, Math.random() < 0.8 ? null : Date.valueOf(dateBroughtIn.plusDays((long) (Math.random() * ChronoUnit.DAYS.between(dateBroughtIn, LocalDate.now())))));
                }
                else{
                    System.out.println("\n"+recordId+"\n");
                    throw new BatchUpdateException();
                }
                insertStmt.addBatch();
                recordId++;

                if (i % batchSize == 0) {
                    long[] batchResult = insertStmt.executeLargeBatch();
                    for (int j = 0; j < batchResult.length; j++) {
                        if (batchResult[j] == Statement.EXECUTE_FAILED) {
                            // Handle duplicate email address
                            throw new BatchUpdateException();
                        }
                    }
                    conn.commit();
                }
            }

            long[] batchResult = insertStmt.executeLargeBatch();
            for (int j = 0; j < batchResult.length; j++) {
                if (batchResult[j] == Statement.EXECUTE_FAILED) {
                    // Handle duplicate email address
                    throw new BatchUpdateException();
                }
            }
            conn.commit();

            // Add constraints
            PreparedStatement addStmt = conn.prepareStatement("ALTER TABLE animal ENABLE TRIGGER ALL;");
            addStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
