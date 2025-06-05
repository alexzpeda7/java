import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

public class SelectUserTable {

    public static void main(String[] args) {
        // Database credentials
        String DB_URL = "jdbc:mysql://localhost:3306/usuarios"; // Replace with your database name
        String USER = "root"; // Replace with your MySQL username
        String PASS = "password"; // Replace with your MySQL password

        // SQL query to select all content from 'usuario' table
        String sql = "SELECT id, login, password FROM usuario";

        try {
            LocalDateTime ini = LocalDateTime.now();
            // 1. Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // 2. Create a statement to execute SQL query
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3. Execute the query and retrieve the result
            ResultSet rs = stmt.executeQuery();

            LocalDateTime fin = LocalDateTime.now();
            System.out.println(Duration.between(ini, fin).toMillis() + " ms");
            
            // 4. Process the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");

                // Print the data from the 'usuario' table
                System.out.println("ID: " + id + ", Login: " + login + ", Password: " + password);
            }

            // 5. Close the resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle any SQL errors
            e.printStackTrace();
        }
    }
}
