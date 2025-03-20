import CalendarManager.CalendarManager;
import Event.Action.EventHandlerAction.*;
import Event.Action.UserAction.LogIn;
import Event.Action.UserAction.SignIn;
import Event.Action.UserAction.UserActionList;

import java.util.*;

import User.User;

public class Main {
    public static void main(String[] args) {

        CalendarManager calendar = new CalendarManager();

        UserActionList userActions = new UserActionList();
        userActions.addAction(new LogIn());
        userActions.addAction(new SignIn());

        EventHandlerList eventActions = new EventHandlerList();
        eventActions.addAction(new DisplayEventAction(calendar));
        eventActions.addAction(new AddRDVEventAction(calendar));
        eventActions.addAction(new AddReunionEventAction(calendar));
        eventActions.addAction(new AddPeriodique(calendar));
        eventActions.addAction(new RemoveEvent(calendar));
        eventActions.addAction(new LogOutEventAction(calendar));

        Scanner scanner = new Scanner(System.in);
        User user = null;
        boolean continuer = true;

        int command;

        while (true) {

            if (user == null) {
                displayTitle();

                System.out.println(userActions.display());
                System.out.println("Choix : ");
                command = Integer.parseInt(scanner.nextLine());
                user = userActions.handle(command);
                eventActions.setCurrentUser(user);
            }

            while (continuer && user != null) {
                System.out.println("\nBonjour, " + user.getUsername());
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println(eventActions.display());
                System.out.print("Votre choix : ");

                command = Integer.parseInt(scanner.nextLine());

                continuer = eventActions.handle(command);
            }
        }
    }

    private static void displayTitle() {
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println(
                "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println(
                "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println(
                "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(
                " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println(
                "                                                                                   __/ |");
        System.out.println(
                "                                                                                  |___/");
    }
}
