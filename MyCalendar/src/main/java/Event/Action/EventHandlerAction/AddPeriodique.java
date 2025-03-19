package Event.Action.EventHandlerAction;

import CalendarManager.CalendarManager;
import Event.DateDebut;
import Event.DureeEvenement;
import Event.Periodique.Frequence;
import Event.Periodique.Periodique;
import Event.TitreEvenement;
import User.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddPeriodique extends EventAction {
    public AddPeriodique(CalendarManager calendar) {
        super(calendar);
        actionDescription = "Ajouter un événement periodique";
    }

    @Override
    public boolean handle(User owner) {
        Scanner scanner = new Scanner(System.in);
        // Ajout simplifié d'une periodique
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
        System.out.print("Frequence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        calendar.ajouterEvent(new Periodique(titre, owner,
                new DateDebut(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)),new DureeEvenement(duree),new Frequence(frequence)));

        System.out.println("Événement ajouté.");
        return true;
    }
}
