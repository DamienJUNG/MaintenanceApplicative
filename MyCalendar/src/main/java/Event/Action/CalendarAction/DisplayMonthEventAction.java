package Event.Action.CalendarAction;

import CalendarManager.CalendarManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DisplayMonthEventAction extends CalendarAction{

    public DisplayMonthEventAction(){
        actionDescription = "Afficher les événements d'un MOIS précis";
    }

    @Override
    public boolean handle(CalendarManager calendar) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
        return true;
    }
}
