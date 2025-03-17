package Event.Action;

import User.User;

public abstract class UserAction {
    public abstract String display(int index);
    public abstract User handle();
}
