package Event.Action.EventHandlerAction;

import CalendarManager.CalendarManager;
import Event.Action.Action;
import User.User;

public abstract class EventAction extends Action {
    protected CalendarManager calendar;
    public EventAction(CalendarManager calendar) {
        this.calendar = calendar;
    }

    public abstract boolean handle(User owner);
}
