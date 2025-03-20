package Event;

import User.User;

import java.time.LocalDateTime;

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

    public boolean isInPeriod(LocalDateTime debut, LocalDateTime fin) {
        return dateDebut.getDateDebut().isAfter(debut) && dateDebut.getDateDebut().isBefore(fin);
    }

    public EventList occurencesInPeriod(LocalDateTime start, LocalDateTime end) {
        EventList eventList = new EventList();
        if(isInPeriod(start, end)) {
            eventList.addEvent(this);
        }
        return eventList;
    }

    public boolean checkConflit(Event event) {
        LocalDateTime fin1 = getDateDebut().getDateDebut().plusMinutes(getDureeEvenement().getDuree());
        LocalDateTime fin2 = event.getDateDebut().getDateDebut().plusMinutes(event.getDureeEvenement().getDuree());

        return getDateDebut().getDateDebut().isBefore(fin2) && fin1.isAfter(event.getDateDebut().getDateDebut());
    }
}