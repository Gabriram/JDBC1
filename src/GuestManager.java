import java.sql.*;

public class GuestManager {

    public void insertGuest(String name, String email, String phone) {
        String sqlQuery = "INSERT INTO ospiti (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore guest: " + e.getMessage());
        }
    }

    public void getAllGuests() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlQuery = "SELECT * FROM ospiti";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                System.out.println(name + " | " + email + " | " + phone);
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
