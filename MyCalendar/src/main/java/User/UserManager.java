package User;

public class UserManager {
    private static UserManager instance = null;
    private final UserList userList = new UserList();

    private UserManager(){
    }

    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();
            instance.createUser(new User("Roger","Chat"));
            instance.createUser(new User("Pierre","KiRouhl"));
        }
        return instance;
    }

    public void createUser(User user){
        userList.addUser(user);
    }
    public boolean checkUser(User user){
        return userList.contains(user);
    }
}
