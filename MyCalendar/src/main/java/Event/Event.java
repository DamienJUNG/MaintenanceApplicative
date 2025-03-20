package Event;

import User.User;

public abstract class Event {
    private static int ID = 1;
    private final int id;
    protected final TitreEvenement title;
    protected final User owner;
    protected final DateDebut dateDebut;
    protected final DureeEvenement dureeEvenement;

    public Event(TitreEvenement title, User owner, DateDebut dateDebut, DureeEvenement dureeEvenement) {
        this.title = title;
        this.owner = owner;
        this.dateDebut = dateDebut;
        this.dureeEvenement = dureeEvenement;
        id = ID;
        ID++;
    }

    public abstract String description();

    public DateDebut getDateDebut() {
        return dateDebut;
    }

    public DureeEvenement getDureeEvenement() {
        return dureeEvenement;
    }

    public User getOwner() {
        return owner;
    }

    public TitreEvenement getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

}