package Event;

public abstract class Event {
    public TitreEvenement title;
    public Proprietaire proprietaire;
    public DateDebut dateDebut;
    public DureeEvenement dureeEvenement;

    public Event(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeEvenement) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeEvenement = dureeEvenement;
    }

    public abstract String description();
}