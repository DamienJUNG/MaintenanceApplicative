import CalendarManager.CalendarManager;
import Event.Action.CalendarAction.*;
import Event.Action.EventHandlerAction.*;
import Event.Action.UserAction.LogIn;
import Event.Action.UserAction.SignIn;
import Event.Action.UserAction.UserActionList;
import Event.DateDebut;
import Event.Event;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

import Event.*;
import Event.Periodique.Frequence;
import Event.Periodique.Periodique;
import Event.RDV.RDV;
import Event.Reunion.LieuReunion;
import Event.Reunion.Participant;
import Event.Reunion.ParticipantList;
import Event.Reunion.Reunion;
import User.User;
import User.UserManager;

public class Main {
    public static void main(String[] args) {

        UserManager userManager = UserManager.getInstance();

        CalendarManager calendar = new CalendarManager();

        UserActionList userActions = new UserActionList();
        userActions.addAction(new LogIn());
        userActions.addAction(new SignIn());

        EventHandlerList eventActions = new EventHandlerList();
        eventActions.addAction(new DisplayEventAction(calendar));
        eventActions.addAction(new AddRDVEventAction(calendar));
        eventActions.addAction(new AddReunionEventAction(calendar));
        eventActions.addAction(new AddPeriodique(calendar));
        eventActions.addAction(new LogOutEventAction(calendar));

        Scanner scanner = new Scanner(System.in);
        User utilisateur = null;
        boolean continuer = true;

        int command;

        while (true) {

            if (utilisateur == null) {
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

                System.out.println(userActions.display());
                System.out.println("Choix : ");
                command = Integer.parseInt(scanner.nextLine());
                utilisateur = userActions.handle(command);
            }

            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur.getUsername());
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                eventActions.display();
                System.out.print("Votre choix : ");

                command = Integer.parseInt(scanner.nextLine());

                continuer = eventActions.handle(command);
            }
        }
    }
}
