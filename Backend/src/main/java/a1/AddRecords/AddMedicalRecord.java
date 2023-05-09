package a1.AddRecords;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

@Component
public class AddMedicalRecord implements IAddRecords {

    private final DataSource dataSource;

    public AddMedicalRecord(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addRecords() {
        int batchSize = 1000;
        int numRecords = 1000000;

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            // Drop constraints
            PreparedStatement dropStmt = conn.prepareStatement("ALTER TABLE medical_record DISABLE TRIGGER ALL;");
            dropStmt.executeUpdate();

            // Insert data
            Faker faker = new Faker();
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO medical_record (medical_record_id, date_of_birth, weight, vaccine, special_care) VALUES (nextval('medical_record_sequence'), ?, ?, ?, ?)");

            for (int i = 1; i <= numRecords; i++) {
                long daysBetween = (long) 15*365;

                // Generate a random number of days between 0 and daysBetween
                long randomDays = (long) (Math.random() * daysBetween);

                // Add the random number of days to the start date to get the random date
                LocalDate dateOfBirth = LocalDate.now().minusDays(randomDays);

                int weight = faker.number().numberBetween(1, 50);
                boolean vaccine = faker.bool().bool();
                boolean specialCare = faker.bool().bool();

                insertStmt.setDate(1, Date.valueOf(dateOfBirth));
                insertStmt.setInt(2, weight);
                insertStmt.setBoolean(3, vaccine);
                insertStmt.setBoolean(4, specialCare);

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

            long[] batchResult = insertStmt.executeLargeBatch();
            for (int j = 0; j < batchResult.length; j++) {
                if (batchResult[j] == Statement.EXECUTE_FAILED) {
                    // Handle duplicate email address
                    throw new BatchUpdateException();
                }
            }
            conn.commit();

            // Add constraints
            PreparedStatement addStmt = conn.prepareStatement("ALTER TABLE medical_record ENABLE TRIGGER ALL;");
            addStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
