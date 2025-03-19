package Event.Action.UserAction;

import User.User;
import User.UserManager;

import java.util.Scanner;

public class LogIn extends UserAction {
    private final UserManager userManager;
    public LogIn(){
        userManager = UserManager.getInstance();
        actionDescription = "Se connecter";
    }

    public User handle(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom d'utilisateur: ");
        String username = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String password = scanner.nextLine();
        User newUser = new User(username, password);
        if(userManager.checkUser(newUser))
            return newUser;
        return null;
    }
}
