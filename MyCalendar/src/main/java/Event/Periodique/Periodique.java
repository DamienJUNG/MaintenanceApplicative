package Event.Periodique;

import Event.DureeEvenement;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;
import Event.EventList;
import User.User;

import java.time.LocalDateTime;

public class Periodique extends Event {
    private final Frequence frequence;

    public Periodique(TitreEvenement title, User owner, DateDebut dateDebut, DureeEvenement dureeEvenement, Frequence frequence) {
        super(title, owner, dateDebut, dureeEvenement);
        this.frequence = frequence;
    }

    @Override
    public String description() {
        return getId()+" - Événement périodique : " + title.getTitre() + " tous les " + frequence.getJours() + " jours";
    }

    public Frequence getFrequence() {
        return frequence;
    }

    @Override
    public EventList occurencesInPeriod(LocalDateTime start, LocalDateTime end) {
        EventList events = new EventList();
        LocalDateTime temp = getDateDebut().getDateDebut();
        while (temp.isBefore(end)) {
            if (!temp.isBefore(start)) {
                events.addEvent(this);
                break;
            }
            temp = temp.plusDays(getFrequence().getJours());
        }
        return events;
    }

    @Override
    public boolean checkConflit(Event event) {
        return false; // Simplification abusive :)
    }
}
