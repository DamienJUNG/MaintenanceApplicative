package Event.Action.UserAction;

import Event.Action.ActionList;
import User.User;

public class UserActionList extends ActionList<UserAction, User> {
    public UserActionList() {
        super(UserAction::handle);
    }
}
