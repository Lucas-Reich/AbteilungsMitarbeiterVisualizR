import java.util.List;

public interface IDatenhaltung {
    void saveAbteilung(Abteilung abteilung);

    Abteilung getAbteilung(long id);

    List<Abteilung> getAllAbteilungen();

    void updateAbteilung(Abteilung abteilung);

    void deleteAbteilung(long abteilungId);


    void saveMitarbeiter(Mitarbeiter mitarbeiter, long abteilungId);

    Mitarbeiter getMitarbeiter(long id);

    List<Mitarbeiter> getAllMitarbeiter();

    void updateMitarbeiter(Mitarbeiter mitarbeiter);

    void deleteMitarbeiter(long mitarbeiterId);
}
