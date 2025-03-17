package trivia;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayGameShould {
    @Nested
    class WhenPlaying {
        @Test
        public void grantSecondChanceAfterFail() {
            PlayGame.main(null);
        }
    }
}
