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

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterRDV(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes){
        Event rdv = new RDV(title,proprietaire,dateDebut,dureeMinutes);
        events.add(rdv);
    }
    public void ajouterReunion(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes, LieuReunion lieu, ParticipantList participants){
        Event reunion = new Reunion(title,proprietaire,dateDebut,dureeMinutes,lieu,participants);
        events.add(reunion);
    }
    public void ajouterPeriodique(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeMinutes,Frequence frequenceJours){
        Event periodique = new Periodique(title,proprietaire,dateDebut,dureeMinutes,frequenceJours);
        events.add(periodique);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();

        for (Event e : events) {
            if (e instanceof Periodique) {
                Periodique periodique = (Periodique) e;
                LocalDateTime temp = periodique.dateDebut.getDateDebut();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(periodique.getFrequence().getJours());
                }
            } else {
                DateDebut dateDebut = e.dateDebut;
                if (!(dateDebut.getDateDebut().isBefore(debut)) && !( dateDebut.getDateDebut().isAfter(fin))) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.getDateDebut().plusMinutes(e1.dureeEvenement.getDuree());
        LocalDateTime fin2 = e2.dateDebut.getDateDebut().plusMinutes(e2.dureeEvenement.getDuree());

        if (e1 instanceof Periodique || e2 instanceof Periodique) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut.getDateDebut().isBefore(fin2) && fin1.isAfter(e2.dateDebut.getDateDebut())) {
            return true;
        }
        return false;
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }
}