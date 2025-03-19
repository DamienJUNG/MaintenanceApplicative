package Event.Action.EventHandlerAction;

import Event.Action.ActionList;
import Event.Action.CalendarAction.CalendarAction;
import User.User;

import java.util.function.Function;

public class EventHandlerList extends ActionList<EventAction, Boolean> {
    private User currentUser = null;
    public EventHandlerList() {
        super(action -> action.handle(null));
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        handler = action -> action.handle(currentUser);
    }
}
