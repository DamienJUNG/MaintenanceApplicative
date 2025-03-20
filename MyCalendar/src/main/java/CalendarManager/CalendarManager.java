package CalendarManager;

import Event.Event;
import Event.Periodique.Periodique;

import java.time.LocalDateTime;

import Event.EventList;

public class CalendarManager {
    private EventList events;

    public CalendarManager() {
        this.events = new EventList();
    }

    public void ajouterEvent(Event event) {
        if(events.checkConlifts(event))
            events.addEvent(event);
    }

    public EventList eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return events.eventsDansPeriode(debut, fin);
    }

    public void afficherEvenements() {
        System.out.println(events.displayEvents());
    }

    public void supprimerEvenement(int id) {
        events.removeEventById(id);
    }

    public EventList getEvents() {
        return events;
    }
}