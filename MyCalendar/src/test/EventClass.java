import Event.DateDebut;
import Event.Periodique.Frequence;
import Event.Periodique.Periodique;
import Event.Proprietaire;
import Event.RDV.RDV;
import Event.Event;
import Event.Reunion.LieuReunion;
import Event.Reunion.ParticipantList;
import Event.Reunion.Reunion;
import Event.TitreEvenement;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class EventClass {
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ForEachEventType {
        @ParameterizedTest
        @MethodSource("providePrint")
        public void shouldHaveSpecificPrint(Event event, String print) {
            assertThat(event.description()).isEqualTo(print);
        }

        public Stream<Arguments> providePrint() {
            return Stream.of(
                    Arguments.of(new RDV(new TitreEvenement("Rendez-vous docteur"),new Proprietaire("Damien"),new DateDebut(LocalDateTime.of(2020,10,2,5,40)),null),"RDV : Rendez-vous docteur le 2 OCTOBER 2020 à 5h40"),
                    Arguments.of(new Reunion(new TitreEvenement("Soutenance de stage"),new Proprietaire("Damien"),new DateDebut(LocalDateTime.of(2020,10,2,5,40)),null,new LieuReunion("IUT CHARLEMAGNE"),new ParticipantList("Damien,Michel,Robert")),"Réunion : Soutenance de stage à IUT CHARLEMAGNE le 2 OCTOBER 2020 à 5h40 avec Damien, Michel et Robert"),
                    Arguments.of(new Periodique(new TitreEvenement("Ménage de l'appart"), new Proprietaire("Damien"),new DateDebut(LocalDateTime.of(2020,10,2,5,40)),null,new Frequence(3)),"Événement périodique : Ménage de l'appart tous les 3 jours")
                    );
        }


    }
}
