package Event.Action.UserAction;

import User.User;
import User.UserManager;

import java.util.Scanner;

public class SignIn extends UserAction {
    UserManager userManager;
    public SignIn(){
        userManager = UserManager.getInstance();
        actionDescription = "Créer un compte";
    }

    @Override
    public User handle() {
        boolean correct = false;
        User user = null;
        while (!correct){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nom d'utilisateur: ");
            String username = scanner.nextLine();
            System.out.print("Mot de passe: ");
            String password = scanner.nextLine();
            System.out.print("Répéter mot de passe: ");
            if (scanner.nextLine().equals(password)) {
                user = new User(username, password);
                userManager.createUser(user);
                correct = true;
            } else {
                System.out.println("Les mots de passes ne correspondent pas...");
                System.out.print("Souhaitez vous créer un compte ? (y/n)");
                if(scanner.nextLine().equals("n"))
                    correct = true;
            }
        }
        return user;
    }
}
