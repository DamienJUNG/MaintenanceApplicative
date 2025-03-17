package trivia;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class GameShould {

    @Nested
    class WhenAddingPlayer {
        @Test
        public void rejectIfGameIsFull() {
            IGame game = new Game();
            for (int i = 0; i < 6; i++) {
                game.add(i + "");
            }
            boolean is_rejected = game.add("Joueur_de_trop");
            assertFalse(is_rejected, "Should have rejected adding a seventh player");
        }

        @Test
        public void rejectIfGameIsInProgress() {
            IGame game = new Game();
            game.add("JI");
            game.add("JO");
            assertFalse(game.isGameInProgress());
            game.roll(1);
            boolean result = game.add("JÜ");
            assertFalse(result);
            assertTrue(game.isGameInProgress());
        }

        @Test
        public void rejectIdenticalPlayer() {
            IGame game = new Game();
            boolean result;
            result = game.add("JI");
            assertTrue(result);
            result = game.add("JI");
            assertFalse(result, "Should have rejected adding identical player");
        }
    }

    @Nested
    class WhenStartingGame {
        @Test
        public void containAtLeast2Player() {
            Game game = new Game();

            game.add("J1");
            game.add("J2");

            assertTrue(game.isGameValid(),"Game should be valid");
        }

        @Test
        public void throwExceptionIfPlayerIsAlone() {
            Game game = new Game();

            game.add("J1");

            assertThrowsExactly(IllegalStateException.class, () -> game.roll(3));
        }

        @Test
        public void loadQuestionsFromFiles() {

        }
    }

    @Nested
    class WhenNewCategorieIsAdded {
        @Test
        public void playNormally() {
            boolean categoriePresent = false;
            for (int i = 0; i < Categorie.values().length; i++) {
                if (Objects.equals(Categorie.values()[i].toString(), "Géographie")) {
                    categoriePresent = true;
                    break;
                }
            }
            assertTrue(categoriePresent, "Catégorie 'Géographie' non présente !");
        }
    }
}
