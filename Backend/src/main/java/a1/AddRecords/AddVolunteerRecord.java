package a1.AddRecords;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Component
public class AddVolunteerRecord implements IAddRecords {

    private final DataSource dataSource;

    public AddVolunteerRecord(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addRecords() {
        int batchSize = 1000;
        int numRecords = 1000000;

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            // Drop constraints
            PreparedStatement dropStmt = conn.prepareStatement("ALTER TABLE volunteer DISABLE TRIGGER ALL;");
            dropStmt.executeUpdate();

            // Retrieve existing email addresses
            PreparedStatement selectStmt = conn.prepareStatement("SELECT email FROM volunteer");
            ResultSet rs = selectStmt.executeQuery();
            Set<String> existingEmails = new HashSet<>();
            while (rs.next()) {
                existingEmails.add(rs.getString("email"));
            }

            // Insert data
            Faker faker = new Faker();
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO volunteer (volunteer_id, name, code, date_of_birth, email, is_specialist) VALUES (nextval('volunteer_sequence'), ?, ?, ?, ?, ?)");

            for (int i = 1; i <= numRecords; i++) {
                String email = faker.internet().emailAddress();
                while (existingEmails.contains(email)) {
                    email = faker.internet().emailAddress();
                }
                existingEmails.add(email);

                insertStmt.setString(1, faker.name().fullName());
                insertStmt.setLong(2, faker.number().randomNumber(8, true));
                insertStmt.setObject(3, faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                insertStmt.setString(4, email);
                insertStmt.setBoolean(5, faker.bool().bool());

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
            PreparedStatement addStmt = conn.prepareStatement("ALTER TABLE volunteer ENABLE TRIGGER ALL;");
            addStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
