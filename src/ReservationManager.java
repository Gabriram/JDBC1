import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ReservationManager {

    public void createReservation() {
        int nextGuestId = getNextGuestId();
        int nextRoomId = getNextRoomId();

        String sqlQuery = "INSERT INTO prenotazioni (ospiti_id, camera_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setInt(1, nextGuestId);
            pstmt.setInt(2, nextRoomId);
            pstmt.executeUpdate();
            System.out.println("Prenotazione creata - Ospite: " + nextGuestId + " | Camera: " + nextRoomId);
        } catch (SQLException e) {
            System.out.println("Errore prenotazione: " + e.getMessage());
        }
    }

    public void createReservation(int idOspiti, int idCamere) {
        String sqlQuery = "INSERT INTO prenotazioni (ospiti_id, camera_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setInt(1, idOspiti);
            pstmt.setInt(2, idCamere);
            pstmt.executeUpdate();
            System.out.println("Prenotazione creata - Ospite: " + idOspiti + " | Camera: " + idCamere);
        } catch (SQLException e) {
            System.out.println("Errore prenotazione creazioni: " + e.getMessage());
        }
    }

    private int getNextGuestId() {
        // Prendi l'ospite più recente (con ID più alto) appena creato
        String sql = "SELECT MAX(id_ospiti) FROM ospiti";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("DEBUG: ID ospite trovato (MAX): " + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Errore getNextGuestId: " + e.getMessage());
        }
        return 1;
    }

    private int getNextRoomId() {
        // Prendi la camera più recente (con ID più alto) appena creata
        String sql = "SELECT MAX(id_camere) FROM camere";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("DEBUG: ID camera trovato (MAX): " + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Errore getNextRoomId: " + e.getMessage());
        }
        return 1;
    }

    public void getAllReservations() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlQuery = "SELECT * FROM prenotazioni";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idPrenotazioni = rs.getInt("id_prenotazione");
                int idOspiti = rs.getInt("ospiti_id");
                int idCamere = rs.getInt("camera_id");
                System.out.println(idPrenotazioni + " | " + idOspiti + " | " + idCamere);
            }
        } catch (SQLException e) {
            System.out.println("Errore Prenotazione Get Reservations: " + e.getMessage());
        }
    }

    public void getOspitiRoom(int idCamere) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlQuery = "SELECT ospiti.name, camere.id_camere, camere.tipo FROM prenotazioni "
                    + "JOIN ospiti ON prenotazioni.ospiti_id = ospiti.id_ospiti "
                    + "JOIN camere ON prenotazioni.camera_id = camere.id_camere "
                    + "WHERE prenotazioni.camera_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, idCamere);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int idCamera = rs.getInt("id_camere");
                String tipo = rs.getString("tipo");
                System.out.println("Ospite: " + name + " | Camera: " + idCamera + " | Tipo: " + tipo);
            }
        } catch (SQLException e) {
            System.out.println("Errore Prenotazione Get Ospiti Room: " + e.getMessage());
        }
    }
}
