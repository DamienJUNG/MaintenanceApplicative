package Event.Action.UserAction;

import Event.Action.Action;
import User.User;

public abstract class UserAction extends Action {

    public abstract User handle();
}
