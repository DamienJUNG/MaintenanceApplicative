package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DisplayDayEventAction extends CalendarAction {

    public DisplayDayEventAction() {
        actionDescription = "Afficher les événements d'une JOUR précis";
    }

    @Override
    public boolean handle(CalendarManager calendar) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
        return true;
    }

    @Override
    public String display(int index) {
        return index+" - Afficher les événements d'un JOUR précis";
    }
}
