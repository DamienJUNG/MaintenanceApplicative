import Event.Event;
import Event.EventList;
import Event.Periodique.Frequence;
import Event.Periodique.Periodique;
import Event.RDV.RDV;
import Event.DateDebut;
import Event.DureeEvenement;
import Event.Recurrent.ActiveDays;
import Event.Recurrent.Alarme;
import Event.Recurrent.WeekDay;
import Event.Reunion.LieuReunion;
import Event.Reunion.ParticipantList;
import Event.Reunion.Reunion;
import Event.TitreEvenement;
import User.User;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalendarManagerShould {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class WhenUserIsConnected {
        @ParameterizedTest
        @MethodSource("provideEventList")
        public void removeEventFromList(EventList eventList, List<Integer> eventToRemove, EventList finalList){
            for(Integer i : eventToRemove){
                eventList.removeEventById(i);
            }
            for(int i=0;i<eventList.size();i++){
                assertThat(eventList.get(i)).isEqualTo(finalList.get(i));
            }
        }

        public Stream<Arguments> provideEventList() {
            User bob = new User("Bob","bob@gmail.com");
            DateDebut dateDebut = new DateDebut(LocalDateTime.of(2000,12,5,5,5,5));
            DureeEvenement dureeEvenement = new DureeEvenement(30);
            // 1 - 2
            Event rdv1 = new RDV(new TitreEvenement("Rendez-vous stage"),bob,dateDebut,dureeEvenement);
            Event rdv2 = new RDV(new TitreEvenement("Rendez-vous plage"),bob,dateDebut,dureeEvenement);

            // 3 - 4
            Event periodic1 = new Periodique(new TitreEvenement("Nettoyage appart"),bob,dateDebut,dureeEvenement,new Frequence(120));
            Event periodic2 = new Periodique(new TitreEvenement("Faire les courses"),bob,dateDebut,dureeEvenement,new Frequence(2));

            // 5 - 6
            Event reunion1 = new Reunion(new TitreEvenement("Soutenance de stage"),bob,dateDebut,dureeEvenement,new LieuReunion("IUT"),new ParticipantList("Michel, Robert"));
            Event reunion2 = new Reunion(new TitreEvenement("Réunion d'information"),bob,dateDebut,dureeEvenement,new LieuReunion("IUT"),new ParticipantList("Tibère, Evan"));

            // 7 - 8
            Event alarme1 = new Alarme(new TitreEvenement("Se réveiller"),bob,dateDebut,dureeEvenement,new ActiveDays(WeekDay.SATURDAY));
            Event alarme2 = new Alarme(new TitreEvenement("Ne pas travailler"),bob,dateDebut,dureeEvenement,new ActiveDays(WeekDay.SATURDAY,WeekDay.SUNDAY,WeekDay.MONDAY,WeekDay.FRIDAY,WeekDay.WEDNESDAY));

            return Stream.of(
                    Arguments.of(new EventList(rdv1,rdv2), Lists.list(1,2),new EventList()),
                    Arguments.of(new EventList(alarme1,alarme2,reunion1,reunion2,periodic1,periodic2), Lists.list(5,7,3), new EventList(alarme2,reunion2,periodic2)),
                    Arguments.of(new EventList(reunion1, rdv1, periodic1), Lists.list(1), new EventList(reunion1, periodic1)),
                    Arguments.of(new EventList(rdv1, rdv2, periodic1, periodic2, alarme1, alarme2, reunion1, reunion2), Lists.list(2, 4, 6), new EventList(rdv1, periodic1, alarme1,alarme2, reunion1)),
                    Arguments.of(new EventList(reunion1, reunion2), Lists.list(5, 6), new EventList()),
                    Arguments.of(new EventList(rdv1, alarme1, periodic1), Lists.list(10, 20), new EventList(rdv1, alarme1, periodic1)),
                    Arguments.of(new EventList(alarme1, periodic1, reunion1, rdv1, alarme2), Lists.list(4, 1, 3), new EventList(alarme1,reunion1,alarme2))
            );
        }
    }

}
