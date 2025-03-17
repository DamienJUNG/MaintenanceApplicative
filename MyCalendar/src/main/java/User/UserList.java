package User;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;
    public UserList() {
        users = new ArrayList<>();
    }
    public UserList(ArrayList<User> users) {
        this.users = new ArrayList<>(users);
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUser(int index, User user) {
        users.set(index, user);
    }
    public void addUser(User user) {
        users.add(user);
    }

    public boolean contains(User user) {
        return users.contains(user);
    }
    public boolean contains(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
