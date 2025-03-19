package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;
import Event.Action.ActionList;

public class CalendarActionList extends ActionList<CalendarAction, Boolean> {
    private CalendarManager calendar;

    public CalendarActionList(CalendarManager calendar) {
        super(action -> action.handle(calendar));
        this.calendar = calendar;
    }
}
