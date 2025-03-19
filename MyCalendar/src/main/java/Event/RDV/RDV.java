package Event.RDV;

import Event.DureeEvenement;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;
import User.User;

import java.time.LocalDateTime;

public class RDV extends Event {
    public RDV(TitreEvenement title, User owner, DateDebut dateDebut, DureeEvenement dureeEvenement) {
        super(title, owner, dateDebut, dureeEvenement);
    }

    @Override
    public String description() {
        LocalDateTime date = dateDebut.getDateDebut();
        return "RDV : " + title.getTitre() + " le "+ dateDebut;
    }
}
