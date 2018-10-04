import java.sql.*;
import java.util.List;

public class SQLiteHelper implements IDatenhaltung {
    private Connection mConn;

    public SQLiteHelper() {

        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        mConn = conn;
    }

    private long getIdForAbteilung(Abteilung mit) {
      "SELECT id from Mitarbeiter where name = "
    }

    @Override
    public void saveAbteilung(Abteilung abteilung) {

        PreparedStatement stmt = mConn.prepareStatement("");
        long id = getIdForAbteilung(abteilung);
        return new Abteilung(
                id
        );
    }

    @Override
    public Abteilung getAbteilung(long id) {
        return null;
    }

    @Override
    public List<Abteilung> getAllAbteilungen() {
        return null;
    }

    @Override
    public void updateAbteilung(Abteilung abteilung) {

    }

    @Override
    public void deleteAbteilung(long abteilungId) {

    }

    @Override
    public void saveMitarbeiter(Mitarbeiter mitarbeiter, long abteilungId) {

    }

    @Override
    public Mitarbeiter getMitarbeiter(long id) {
        return null;
    }

    @Override
    public List<Mitarbeiter> getAllMitarbeiter() {
        return null;
    }

    @Override
    public void updateMitarbeiter(Mitarbeiter mitarbeiter) {

    }

    @Override
    public void deleteMitarbeiter(long mitarbeiterId) {

    }
}
