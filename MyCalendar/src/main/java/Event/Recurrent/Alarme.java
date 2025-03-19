package Event.Recurrent;

import Event.DateDebut;
import Event.DureeEvenement;
import Event.Event;
import Event.TitreEvenement;
import User.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Alarme extends Event {
    private final ActiveDays activeDays;
    public Alarme(TitreEvenement title, User owner, DateDebut dateDebut, DureeEvenement dureeEvenement, ActiveDays activeDays) {
        super(title, owner, dateDebut, dureeEvenement);
        this.activeDays = activeDays;
    }

    @Override
    public String description() {
        StringBuilder builder = new StringBuilder();
        builder.append("Alarme : ").append(getTitle().getTitre());
        if(activeDays==null || activeDays.getActiveDays().isEmpty()){
            builder.append(" inactif");
        }
        else {
            builder.append(" actif ");
            for (Iterator<WeekDay> iterator = activeDays.getActiveDays().iterator(); iterator.hasNext(); ) {
                WeekDay weekDay = iterator.next();
                builder.append(weekDay.toString());
                if(iterator.hasNext()){
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }
}
