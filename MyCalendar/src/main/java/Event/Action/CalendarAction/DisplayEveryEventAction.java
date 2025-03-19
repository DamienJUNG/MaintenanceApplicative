package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;

public class DisplayEveryEventAction extends CalendarAction {
    @Override
    public boolean handle(CalendarManager calendar) {
        calendar.afficherEvenements();
        return true;
    }
}
