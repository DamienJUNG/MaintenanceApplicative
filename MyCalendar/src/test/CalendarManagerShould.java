import CalendarManager.CalendarManager;
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
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarManagerShould {

    @BeforeAll
    static void setUp() {
        User bob = new User("Bob", "bob@gmail.com");

        LocalDateTime tempsDebut = LocalDateTime.of(2000, 12, 5, 5, 5, 5);
        LocalDateTime tempsFin = LocalDateTime.of(2001, 6, 10, 10, 10, 10);
        DureeEvenement dureeEvenement = new DureeEvenement(30);

        rdv1 = new RDV(new TitreEvenement("Rendez-vous stage"), bob, new DateDebut(tempsDebut), dureeEvenement);
        rdv2 = new RDV(new TitreEvenement("Rendez-vous plage"), bob, new DateDebut(tempsFin), dureeEvenement);

        periodic1 = new Periodique(new TitreEvenement("Nettoyage appart"), bob, new DateDebut(tempsDebut.plusHours(1)), dureeEvenement, new Frequence(15));
        periodic2 = new Periodique(new TitreEvenement("Faire les courses"), bob,  new DateDebut(tempsDebut.plusHours(3)), dureeEvenement, new Frequence(3));

        reunion1 = new Reunion(new TitreEvenement("Soutenance de stage"), bob,  new DateDebut(tempsDebut.plusHours(4)), dureeEvenement, new LieuReunion("IUT"), new ParticipantList("Michel, Robert"));
        reunion2 = new Reunion(new TitreEvenement("Réunion d'information"), bob, new DateDebut(tempsFin.plusHours(2)), dureeEvenement, new LieuReunion("IUT"), new ParticipantList("Tibère, Evan"));

        alarme1 = new Alarme(new TitreEvenement("Se réveiller"), bob,  new DateDebut(tempsDebut.plusHours(8)), dureeEvenement, new ActiveDays(WeekDay.SATURDAY));
        alarme2 = new Alarme(new TitreEvenement("Ne pas travailler"), bob,  new DateDebut(tempsDebut.plusHours(6)), dureeEvenement, new ActiveDays(WeekDay.SATURDAY, WeekDay.SUNDAY, WeekDay.MONDAY, WeekDay.FRIDAY, WeekDay.WEDNESDAY));

    }

    static Event rdv1;
    static Event rdv2;
    static Event periodic1;
    static Event periodic2;
    static Event reunion1;
    static Event reunion2;
    static Event alarme1;
    static Event alarme2;

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class WhenRemovingEvents {
        @ParameterizedTest
        @MethodSource("provideEventList")
        public void returnCorrectList(EventList eventList, List<Integer> eventToRemove, EventList finalList){
            for(Integer i : eventToRemove){
                eventList.removeEventById(i);
            }
            for(int i=0;i<eventList.size();i++){
                assertThat(eventList.get(i)).isEqualTo(finalList.get(i));
            }
        }

        public Stream<Arguments> provideEventList() {

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

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class WhenCheckingEventInsidePeriodics {
        
        CalendarManager calendar;

        @BeforeEach
        void setUp() {
            calendar = new CalendarManager();
        }

        @ParameterizedTest
        @MethodSource("providePeriodsForEventFiltering")
        void testEventsDansPeriode(EventList eventList, LocalDateTime debut, LocalDateTime fin, EventList expected) {
            for (int i = 0; i < eventList.size(); i++) {
                calendar.ajouterEvent(eventList.get(i));
            }
            EventList result = calendar.eventsDansPeriode(debut, fin);

            assertThat(result.displayEvents()).isEqualTo(expected.displayEvents());
        }


        Stream<Arguments> providePeriodsForEventFiltering() {

            LocalDateTime startOfDay = LocalDateTime.of(2000, 12, 5, 0, 0);
            LocalDateTime endOfDay = startOfDay.plusDays(1);

            LocalDateTime endOfMonth = startOfDay.plusMonths(1);

            LocalDateTime endOfYear = startOfDay.plusYears(1);

            EventList eventList = new EventList(rdv1, rdv2, periodic1, periodic2, reunion1, reunion2, alarme1, alarme2);

            return Stream.of(
                    Arguments.of(eventList, startOfDay, endOfDay,
                            new EventList(rdv1, periodic1,periodic2,reunion1, alarme1, alarme2)),

                    Arguments.of(eventList, startOfDay, endOfMonth,
                            new EventList(rdv1, periodic1, periodic2, reunion1, alarme1, alarme2)),

                    Arguments.of(eventList, startOfDay, endOfYear,
                            new EventList(rdv1, rdv2, periodic1, periodic2, reunion1,reunion2, alarme1, alarme2))
            );
        }

    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class WhenAddingNewEvent {
        @ParameterizedTest
        @MethodSource("provideEventsForAdding")
        void testAjouterEvent(EventList initialList, Event eventToAdd, EventList expectedList) {
            initialList.addEvent(eventToAdd);
            assertEquals(expectedList.displayEvents(), initialList.displayEvents(), "La liste d'événements après ajout ne correspond pas à l'attendu.");
        }

        public Stream<Arguments> provideEventsForAdding() {

            EventList emptyEventList = new EventList();
            EventList eventListWithOne = new EventList(rdv1);
            EventList eventListWithMultiple = new EventList(rdv1, rdv2, periodic1);

            return Stream.of(
                    Arguments.of(emptyEventList, rdv1, new EventList(rdv1)),

                    Arguments.of(eventListWithOne, rdv1, new EventList(rdv1)),

                    Arguments.of(eventListWithMultiple, periodic1, new EventList(rdv1, rdv2, periodic1))
            );
        }


    }


}
