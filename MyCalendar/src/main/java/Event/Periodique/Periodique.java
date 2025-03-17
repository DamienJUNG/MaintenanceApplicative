package Event.Periodique;

import Event.DureeEvenement;
import Event.Proprietaire;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;

public class Periodique extends Event {
    private Frequence frequence;

    public Periodique(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeEvenement, Frequence frequence) {
        super(title, proprietaire, dateDebut, dureeEvenement);
        this.frequence = frequence;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequence.getJours() + " jours";
    }

    public Frequence getFrequence() {
        return frequence;
    }

    public void setFrequence(Frequence frequence) {
        this.frequence = frequence;
    }
}
