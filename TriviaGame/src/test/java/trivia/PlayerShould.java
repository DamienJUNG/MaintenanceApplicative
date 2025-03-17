package trivia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerShould {

    @Nested
    class WhenPlayerIsInGame {
        private Game game;
        @BeforeEach
        void setUp() {
            game = new Game();
            game.add("JI");
            game.add("JO");
        }

        @Test
        public void resetStreakAfterFail(){

            for(int i=0;i<6;i++){
                game.roll(3);
                if(i > 3) {
                    game.wrongAnswer();
                }
                else {
                    game.handleCorrectAnswer();
                }
            }

            Player firstPlayer = game.getPlayers().get(0);
            Player secondPlayer = game.getPlayers().get(1);

            assertEquals(0, firstPlayer.getStreak());
            assertEquals(2, firstPlayer.getScore());

            assertEquals(0, secondPlayer.getStreak());
            assertEquals(2, secondPlayer.getScore());
        }

        @Test
        public void streakIncreaseTwice(){

            Player firstPlayer = game.getPlayers().get(0);
            Player secondPlayer = game.getPlayers().get(1);

            for (int i = 0; i < 3; i++)
                secondPlayer.increaseStreak();

            for(int i=0;i<10;i++){
                game.roll(3);
                game.handleCorrectAnswer();
            }

            assertEquals(5, firstPlayer.getStreak());
            assertEquals(7, firstPlayer.getScore());

            assertEquals(8, secondPlayer.getStreak());
            assertEquals(10, secondPlayer.getScore());
        }
    }
}
