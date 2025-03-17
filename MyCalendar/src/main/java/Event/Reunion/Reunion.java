package Event.Reunion;

import Event.DureeEvenement;
import Event.Proprietaire;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;

public class Reunion extends Event {
    private final LieuReunion lieu;
    private final ParticipantList participantList;

    public Reunion(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeEvenement, LieuReunion lieu, ParticipantList participantList) {
        super(title, proprietaire, dateDebut, dureeEvenement);
        this.lieu = lieu;
        this.participantList = participantList;
    }

    @Override
    public String description() {
        return "Réunion : "+title.getTitre()+ " à " + lieu.getNom() + " le "+dateDebut+" avec " + participantList.toString();
    }
}
