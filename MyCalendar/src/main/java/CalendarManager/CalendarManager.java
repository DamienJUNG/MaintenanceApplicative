package CalendarManager;

import Event.Event;
import Event.Periodique.Periodique;

import java.time.LocalDateTime;

import Event.EventList;

public class CalendarManager {
    public EventList events;

    public CalendarManager() {
        this.events = new EventList();
    }

    public void ajouterEvent(Event event) {
        events.addEvent(event);
    }

    public EventList eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return events.eventsDansPeriode(debut, fin);
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.getDateDebut().getDateDebut().plusMinutes(e1.getDureeEvenement().getDuree());
        LocalDateTime fin2 = e2.getDateDebut().getDateDebut().plusMinutes(e2.getDureeEvenement().getDuree());

        if (e1 instanceof Periodique || e2 instanceof Periodique) {
            return false; // Simplification abusive
        }

        if (e1.getDateDebut().getDateDebut().isBefore(fin2) && fin1.isAfter(e2.getDateDebut().getDateDebut())) {
            return true;
        }
        return false;
    }

    public void afficherEvenements() {
        System.out.println(events.displayEvents());
    }

    public void supprimerEvenement(int id) {
        events.removeEventById(id);
    }
}