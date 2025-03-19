package Event.Action.EventHandlerAction;

import CalendarManager.CalendarManager;
import Event.DateDebut;
import Event.DureeEvenement;
import Event.RDV.RDV;
import Event.TitreEvenement;
import User.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddRDVEventAction extends EventAction {

    public AddRDVEventAction(CalendarManager calendar) {
        super(calendar);

        actionDescription = "Ajouter un rendez-vous perso";
    }

    @Override
    public boolean handle(User owner) {
        Scanner scanner = new Scanner(System.in);
        // Ajout simplifié d'un RDV personnel
        System.out.print("Titre de l'événement : ");
        TitreEvenement titre = new TitreEvenement(scanner.nextLine());
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());

        calendar.ajouterEvent(new RDV(titre, owner,
                new DateDebut(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)), new DureeEvenement(duree)));

        System.out.println("Événement ajouté.");
        return true;
    }
}
