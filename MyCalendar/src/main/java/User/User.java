package User;

public class User {
    private final int id = 0;
    private final String username;
    private final String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
