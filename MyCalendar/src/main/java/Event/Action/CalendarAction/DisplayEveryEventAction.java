package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;

public class DisplayEveryEventAction extends CalendarAction {
    public DisplayEveryEventAction() {
        actionDescription = "Afficher TOUS les événements";
    }
    @Override
    public boolean handle(CalendarManager calendar) {
        calendar.afficherEvenements();
        return true;
    }
}
