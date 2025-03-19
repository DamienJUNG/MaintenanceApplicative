package User;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;
    public UserList() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean contains(User user) {
        for (User currentUser : users) {
            if(currentUser.getPassword().equals(user.getPassword()) && currentUser.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
