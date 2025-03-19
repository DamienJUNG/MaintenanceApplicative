package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;

public class CalendarReturnAction extends CalendarAction {
    public CalendarReturnAction() {
        actionDescription = "Retour";
    }

    @Override
    public boolean handle(CalendarManager calendar) {
        return true;
    }
}
