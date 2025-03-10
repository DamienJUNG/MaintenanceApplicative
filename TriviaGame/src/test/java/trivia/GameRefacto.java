package trivia;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameRefacto {

    @Test
    public void testMax6PLayer(){
        IGame game = new Game();
        for (int i = 0; i < 6; i++) {
            game.add(i + "");
        }
        boolean is_rejected = game.add("Joueur_de_trop");
        assertFalse(is_rejected, "Should have rejected adding a seventh player");
    }

    @Test
    public void testNewCategorie() {
        boolean categoriePresent = false;
        for (int i = 0; i < Categorie.values().length; i++) {
            if (Objects.equals(Categorie.values()[i].toString(), "Géographie")) {
                categoriePresent = true;
                break;
            }
        }
        assertTrue(categoriePresent, "Catégorie 'Géographie' non présente !");
    }

    @Test
    public void testMinPlayers() {
        IGame game = new Game();
        //Test à 0

        //Test à 1
        game.add("J1");
        //Test à 2
        game.add("J2");

        game.isGameValid();
    }

    @Test
    public void testPlayerEnterAfterGameStart() {
        IGame game = new Game();
        game.add("JI");
        game.add("JO");
        assertFalse(game.isGameInProgress());
        game.roll(42);
        boolean result = game.add("JÜ");
        assertFalse(result);
        assertTrue(game.isGameInProgress());
    }

    @Test
    public void testTwoPlayerSameName() {
        IGame game = new Game();
        boolean result;
        result = game.add("JI");
        assertTrue(result);
        result = game.add("JI");
        assertFalse(result);
    }
}
