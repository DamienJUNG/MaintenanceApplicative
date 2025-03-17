import Event.Event;
import Event.Periodique.Frequence;
import Event.Periodique.Periodique;
import Event.RDV.RDV;
import Event.Reunion.LieuReunion;
import Event.Reunion.ParticipantList;
import Event.Reunion.Reunion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Event.DureeEvenement;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Proprietaire;
import Event.EventList;

public class CalendarManager {
    public EventList events;

    public CalendarManager() {
        this.events = new EventList();
    }

    public void ajouterEvent(Event event) {
        events.addEvent(event);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();

        for (Event e : events.getEvents()) {
            if (e instanceof Periodique) {
                Periodique periodique = (Periodique) e;
                LocalDateTime temp = periodique.getDateDebut().getDateDebut();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(periodique.getFrequence().getJours());
                }
            } else {
                DateDebut dateDebut = e.getDateDebut();
                if (!(dateDebut.getDateDebut().isBefore(debut)) && !( dateDebut.getDateDebut().isAfter(fin))) {
                    result.add(e);
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
        for (Event e : events.getEvents()) {
            System.out.println(e.description());
        }
    }
}