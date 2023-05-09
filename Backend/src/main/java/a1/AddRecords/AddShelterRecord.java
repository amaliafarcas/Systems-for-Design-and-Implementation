package a1.AddRecords;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class AddShelterRecord implements IAddRecords {

    private final DataSource dataSource;

    public AddShelterRecord(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addRecords() {
        int batchSize = 1000;
        int numRecords = 1000000;

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            // Drop constraints
            PreparedStatement dropStmt = conn.prepareStatement("ALTER TABLE shelter DISABLE TRIGGER ALL;");
            dropStmt.executeUpdate();

            // Retrieve existing email addresses
            PreparedStatement selectPhoneStmt = conn.prepareStatement("SELECT phone_number FROM shelter");
            ResultSet rsPhone = selectPhoneStmt.executeQuery();
            Set<Long> existingPhone = new HashSet<>();
            while (rsPhone.next()) {
                existingPhone.add(rsPhone.getLong("phone_number"));
            }

            // Retrieve existing email addresses
            PreparedStatement selectCodeStmt = conn.prepareStatement("SELECT postal_code FROM shelter");
            ResultSet rsCode = selectCodeStmt.executeQuery();
            Set<Long> existingCode = new HashSet<>();
            while (rsCode.next()) {
                existingCode.add(rsCode.getLong("postal_code"));
            }

            // Insert data
            Faker faker = new Faker();
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO shelter (shelter_id, capacity, city, name,  phone_number, postal_code) VALUES (nextval('shelter_sequence'), ?, ?, ?, ?, ?)");

            for (int i = 1; i <= numRecords; i++) {

                Long phoneNumber = Long.parseLong("40" + faker.number().randomNumber(9, true));
                while (existingPhone.contains(phoneNumber)) {
                    phoneNumber = Long.parseLong("40" + faker.number().randomNumber(9, true));
                }
                existingPhone.add(phoneNumber);

                Long postalCode = faker.number().randomNumber(8, true);
                while (existingCode.contains(postalCode)) {
                    postalCode = faker.number().randomNumber(8, true);
                }
                existingCode.add(postalCode);

                insertStmt.setInt(1, faker.number().numberBetween(1, 1000));
                insertStmt.setString(2, faker.address().city());
                insertStmt.setString(3, faker.company().name());
                insertStmt.setLong(4, phoneNumber);
                insertStmt.setLong(5, postalCode);

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
            PreparedStatement addStmt = conn.prepareStatement("ALTER TABLE shelter ENABLE TRIGGER ALL;");
            addStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
