package trivia;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int score;
    private int position;
    private boolean isInJail;
    private boolean hasSecondChance;

    private int streak;

    Player(String name) {
        this.name = name;
        this.score = 0;
        this.position = 1;
        this.isInJail = false;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }

    public int getStreak() {
        return streak;
    }

    public void increaseStreak() {
        streak++;
    }

    public void resetStreak() {
        streak = 0;
    }

    public boolean hasSecondChance() {
        return hasSecondChance;
    }
    public void setSecondChance(boolean hasSecondChance) {
        this.hasSecondChance = hasSecondChance;
    }
}
