package Event.Reunion;

import Event.DureeEvenement;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;
import User.User;

public class Reunion extends Event {
    private final LieuReunion lieu;
    private final ParticipantList participantList;

    public Reunion(TitreEvenement title, User owner, DateDebut dateDebut, DureeEvenement dureeEvenement, LieuReunion lieu, ParticipantList participantList) {
        super(title, owner, dateDebut, dureeEvenement);
        this.lieu = lieu;
        this.participantList = participantList;
    }

    @Override
    public String description() {
        return getId()+" - Réunion : "+title.getTitre()+ " à " + lieu.getNom() + " le "+dateDebut+" avec " + participantList.toString();
    }
}
