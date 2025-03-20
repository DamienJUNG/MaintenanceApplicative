package CalendarManager;

import Event.Event;
import Event.Periodique.Periodique;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Event.DateDebut;
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
        EventList result = new EventList();

        for (int i=0;i< events.size();i++) {
            Event e = events.get(i);
            if (e instanceof Periodique periodique) {
                LocalDateTime temp = periodique.getDateDebut().getDateDebut();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.addEvent(e);
                        break;
                    }
                    temp = temp.plusDays(periodique.getFrequence().getJours());
                }
            } else {
                DateDebut dateDebut = e.getDateDebut();
                if (!(dateDebut.getDateDebut().isBefore(debut)) && !( dateDebut.getDateDebut().isAfter(fin))) {
                    result.addEvent(e);
                }
            }
        }
        return result;
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