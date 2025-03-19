package Event;

public class TitreEvenement {
    private final String titre;
    public TitreEvenement(String titre) {
        this.titre = titre.trim();
    }

    public String getTitre() {
        return titre;
    }
}
