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

        UserManager userManager = new UserManager();

        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        User utilisateur = null;
        boolean continuer = true;

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

                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Choix : ");

                String username;
                String password;
                switch (scanner.nextLine()) {
                    case "1":
                        System.out.print("Nom d'utilisateur: ");
                        username = scanner.nextLine();
                        System.out.print("Mot de passe: ");
                        password = scanner.nextLine();
                        userManager.checkUser(new User(username,password));
                        break;

                    case "2":
                        System.out.print("Nom d'utilisateur: ");
                        username = scanner.nextLine();
                        System.out.print("Mot de passe: ");
                        password = scanner.nextLine();
                        System.out.print("Répéter mot de passe: ");
                        if (scanner.nextLine().equals(password)) {
                            userManager.createUser(new User(username,password));
                        } else {
                            System.out.println("Les mots de passes ne correspondent pas...");
                            utilisateur = null;
                        }
                        break;
                }
            }

            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur);
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un rendez-vous perso");
                System.out.println("3 - Ajouter une réunion");
                System.out.println("4 - Ajouter un évènement périodique");
                System.out.println("5 - Se déconnecter");
                System.out.print("Votre choix : ");

                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        System.out.println("\n=== Menu de visualisation d'Événements ===");
                        System.out.println("1 - Afficher TOUS les événements");
                        System.out.println("2 - Afficher les événements d'un MOIS précis");
                        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
                        System.out.println("4 - Afficher les événements d'un JOUR précis");
                        System.out.println("5 - Retour");
                        System.out.print("Votre choix : ");

                        choix = scanner.nextLine();

                        switch (choix) {
                            case "1":
                                calendar.afficherEvenements();
                                break;

                            case "2":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeMois = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int mois = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
                                LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
                                break;

                            case "3":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeSemaine = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le numéro de semaine (1-52) : ");
                                int semaine = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutSemaine = LocalDateTime.now()
                                        .withYear(anneeSemaine)
                                        .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                                        .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                                        .withHour(0).withMinute(0);
                                LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
                                break;

                            case "4":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeJour = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int moisJour = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le jour (1-31) : ");
                                int jour = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
                                LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
                                break;
                        }
                        break;

                    case "2":
                        ajouterRDV(scanner, calendar, utilisateur.getUsername());
                        break;

                    case "3":
                        ajoutReunion(scanner, utilisateur.getUsername(), calendar);
                        break;

                        case "4":
                            ajoutPeriodique(scanner, calendar, utilisateur.getUsername());
                            break;

                    default:
                        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
                        continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");

                        utilisateur = null;
                }
            }
        }
    }

    private static void ajouterRDV(Scanner scanner, CalendarManager calendar, String utilisateur) {
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

        calendar.ajouterEvent(new RDV(titre, new Proprietaire(utilisateur),
                new DateDebut(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)), new DureeEvenement(duree)));

        System.out.println("Événement ajouté.");
    }

    private static void ajoutReunion(Scanner scanner, String utilisateur, CalendarManager calendar) {
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
        participants.addParticipant(new Participant(utilisateur));

        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui"))
        {
            System.out.print("Participants : " + participants);
            participants.addParticipant(new Participant(scanner.nextLine()));
        }

        calendar.ajouterEvent(new Reunion(titre, new Proprietaire(utilisateur),
                new DateDebut(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)), new DureeEvenement(duree),
                new LieuReunion(lieu), participants));

        System.out.println("Événement ajouté.");
    }

    private static void ajoutPeriodique(Scanner scanner, CalendarManager calendar, String utilisateur) {
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

        calendar.ajouterEvent(new Periodique(titre, new Proprietaire(utilisateur),
                new DateDebut(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute)),new DureeEvenement(duree),new Frequence(frequence)));

        System.out.println("Événement ajouté.");
    }

    private static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}
