package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;
import Event.Action.Action;
import Event.Event;

import java.util.Calendar;
import java.util.List;

public abstract class CalendarAction extends Action {

    public abstract boolean handle(CalendarManager calendar);

    protected static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}
