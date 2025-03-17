package Event.RDV;

import Event.DureeEvenement;
import Event.Proprietaire;
import Event.TitreEvenement;
import Event.DateDebut;
import Event.Event;

public class RDV extends Event {
    public RDV(TitreEvenement title, Proprietaire proprietaire, DateDebut dateDebut, DureeEvenement dureeEvenement) {
        super(title, proprietaire, dateDebut, dureeEvenement);
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut.toString();
    }
}
