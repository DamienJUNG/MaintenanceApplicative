package Event.Recurrent;

import Event.DateDebut;
import Event.DureeEvenement;
import Event.Event;
import Event.TitreEvenement;
import User.User;

import java.util.ArrayList;
import java.util.List;

public class Alarme extends Event {
    private final ActiveDays activeDays;
    public Alarme(TitreEvenement title, User owner, DateDebut dateDebut, DureeEvenement dureeEvenement, ActiveDays activeDays) {
        super(title, owner, dateDebut, dureeEvenement);
        this.activeDays = activeDays;
    }

    @Override
    public String description() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
