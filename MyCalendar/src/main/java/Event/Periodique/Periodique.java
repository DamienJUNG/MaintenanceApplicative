package Event.Periodique;

import Event.DureeEvenement;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;
import User.User;

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
}
