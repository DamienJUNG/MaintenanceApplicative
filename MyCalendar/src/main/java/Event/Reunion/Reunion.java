package Event.Reunion;

import Event.DureeEvenement;
import Event.Proprietaire;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;

public class Reunion extends Event {
    private LieuReunion lieu;
    private ParticipantList participantList;

    public Reunion(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeEvenement, LieuReunion lieu, ParticipantList participantList) {
        super(title, proprietaire, dateDebut, dureeEvenement);
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participantList.toString();
    }
}
