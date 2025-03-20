package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;
import Event.Action.Action;
import Event.Event;
import Event.EventList;

import java.util.List;

public abstract class CalendarAction extends Action {

    public abstract boolean handle(CalendarManager calendar);

    protected static void afficherListe(EventList evenements) {
        if (evenements.size()!=0) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            evenements.displayEvents();
        }
    }
}
