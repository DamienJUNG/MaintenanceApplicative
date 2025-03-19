package Event.Action.EventHandlerAction;

import CalendarManager.CalendarManager;
import Event.DateDebut;
import Event.DureeEvenement;
import Event.Reunion.LieuReunion;
import Event.Reunion.Participant;
import Event.Reunion.ParticipantList;
import Event.Reunion.Reunion;
import Event.TitreEvenement;
import User.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddReunionEventAction extends EventAction {
    public AddReunionEventAction(CalendarManager calendar) {
        super(calendar);

        actionDescription = "Ajouter une réunion";
    }

    @Override
    public boolean handle(User owner) {
        Scanner scanner = new Scanner(System.in);
        // Ajout simplifié d'une réunion
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
        System.out.println("Lieu :");
        String lieu = scanner.nextLine();

        ParticipantList participants = new ParticipantList();
        participants.addParticipant(new Participant(owner.getUsername()));

        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui"))
        {
            System.out.print("Participants : " + participants);
            participants.addParticipant(new Participant(scanner.nextLine()));
        }

        calendar.ajouterEvent(new Reunion(titre, owner,
                new DateDebut(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)), new DureeEvenement(duree),
                new LieuReunion(lieu), participants));

        System.out.println("Événement ajouté.");
        return true;
    }
}
