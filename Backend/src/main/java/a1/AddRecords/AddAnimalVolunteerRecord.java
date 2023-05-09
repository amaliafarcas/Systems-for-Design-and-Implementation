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
public class AddAnimalVolunteerRecord implements IAddRecords {

    private final DataSource dataSource;

    public AddAnimalVolunteerRecord(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addRecords() {
        int batchSize = 1000;
        int numRecords = 10000000;

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            // Drop constraints
            PreparedStatement dropStmt = conn.prepareStatement("ALTER TABLE volunteer_animal DISABLE TRIGGER ALL;");
            dropStmt.executeUpdate();


            // Insert data
            Faker faker = new Faker();
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO volunteer_animal (animal_id, volunteer_id, assignment_day) VALUES (?, ?, ?)");

            int animalRecordId = 11;
            Set<String> uniquePairs = new HashSet<>();
            for (int i = animalRecordId; i < numRecords + animalRecordId; i++) {
                for (int k = 0; k <= 9; k++) {
                    String pair = "";
                    Long volunteerId = (long) faker.number().numberBetween(11, 1000010);
                    while (true) {
                        pair = i + "-" + volunteerId;
                        if (!uniquePairs.contains(pair)) {
                            uniquePairs.add(pair);
                            break;
                        }
                        volunteerId = (long) faker.number().numberBetween(11, 1000010);
                    }
                    insertStmt.setLong(1, i);
                    insertStmt.setLong(2, volunteerId);

                    PreparedStatement selectDateBroughtIn = conn.prepareStatement("SELECT day_brought_in FROM animal WHERE animal_id = ?");
                    selectDateBroughtIn.setLong(1, i);
                    ResultSet rsBirth = selectDateBroughtIn.executeQuery();
                    if (rsBirth.next()) {
                        LocalDate dayBroughtIn = rsBirth.getDate("day_brought_in").toLocalDate();
                        // Calculate the number of days between the start and end dates
                        long daysBetween = ChronoUnit.DAYS.between(dayBroughtIn, LocalDate.now());

                        // Generate a random number of days between 0 and daysBetween
                        long randomDays = (long) (Math.random() * daysBetween);

                        // Add the random number of days to the start date to get the random date
                        LocalDate assignmentDay = dayBroughtIn.plusDays(randomDays);

                        insertStmt.setDate(3, Date.valueOf(assignmentDay));
                    } else {
                        System.out.println("\n" + i + "\n");
                        throw new BatchUpdateException();
                    }
                    insertStmt.addBatch();

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
            }

            long[] batchResult = insertStmt.executeLargeBatch();
            for (int j = 0; j < batchResult.length; j++) {
                if (batchResult[j] == Statement.EXECUTE_FAILED) {
                    // Handle duplicate email address
                    throw new BatchUpdateException();
                }
            }
            conn.commit();

            faker = null;
            uniquePairs = null; // assuming this is the Set of unique pairs


            // Add constraints
            PreparedStatement addStmt = conn.prepareStatement("ALTER TABLE volunteer_animal ENABLE TRIGGER ALL;");
            addStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
