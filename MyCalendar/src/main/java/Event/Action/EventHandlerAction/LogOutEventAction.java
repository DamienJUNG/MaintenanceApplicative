package Event.Action.EventHandlerAction;

import CalendarManager.CalendarManager;
import User.User;

import java.util.Scanner;

public class LogOutEventAction extends EventAction {
    public LogOutEventAction(CalendarManager calendar) {
        super(calendar);
        actionDescription = "Se déconnecter";
    }

    @Override
    public boolean handle(User owner) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
        boolean continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");

        owner = null;
        return continuer;
    }
}
