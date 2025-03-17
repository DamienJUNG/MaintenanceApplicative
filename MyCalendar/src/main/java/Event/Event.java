package Event;

public abstract class Event {
    protected final TitreEvenement title;
    protected final Proprietaire proprietaire;
    protected final DateDebut dateDebut;
    protected final DureeEvenement dureeEvenement;

    public Event(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeEvenement) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeEvenement = dureeEvenement;
    }

    public abstract String description();

    public DateDebut getDateDebut() {
        return dateDebut;
    }

    public DureeEvenement getDureeEvenement() {
        return dureeEvenement;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public TitreEvenement getTitle() {
        return title;
    }
}