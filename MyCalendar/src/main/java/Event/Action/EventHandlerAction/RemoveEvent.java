package Event.Action.EventHandlerAction;

import CalendarManager.CalendarManager;
import User.User;

import java.util.Scanner;

public class RemoveEvent extends EventAction {
    public RemoveEvent(CalendarManager calendar) {
        super(calendar);
        actionDescription = "Supprimer un événement ";
    }

    @Override
    public boolean handle(User owner) {
        Scanner scanner = new Scanner(System.in);

        calendar.afficherEvenements();
        System.out.println("Entrez le numéro de l'événement à supprimer :");
        int id = scanner.nextInt();
        calendar.supprimerEvenement(id);
        return true;
    }
}
