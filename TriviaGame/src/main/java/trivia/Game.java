package trivia;

import java.io.FileReader;
import java.util.*;

// TODO REFACTOR ME
public class Game implements IGame {
   public static final int MAX_PLAYER_COUNT = 6;
   public static final int MAP_SIZE = 12;

   private final ArrayList<Player> players = new ArrayList<>();

   private boolean gameInProgress = false;
   
   private final Map<Categorie, LinkedList<String>> categoriesQuestions = new HashMap<>();

   private int currentPlayer = 0;
   private boolean isGettingOutOfPenaltyBox;

   public Game() {
      for (Categorie categorie : Categorie.values()) {
         categoriesQuestions.put(categorie, new LinkedList<>());
      }
      for (int i = 1; i < 11; i++) {
         for (Categorie categorie : Categorie.values()) {
            ResourceBundle bundle = ResourceBundle.getBundle(categorie.getFileName());
            try {
               categoriesQuestions.get(categorie).addLast(bundle.getString("q"+i));
            }
            catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   public boolean add(String playerName) {
      if(gameInProgress) {
         return false;
      }

      if(players.size() >= MAX_PLAYER_COUNT) {
         return false;
      }

      if(players.stream().anyMatch(player -> player.getName().equals(playerName))) {
         return false;
      }

      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + numberOfPlayer());
      return true;
   }

   public int numberOfPlayer() {
      return players.size();
   }

   public void roll(int roll) {

      if(!isGameValid()) throw new IllegalStateException("Game is not valid");
      gameInProgress = true;

      Player p = players.get(currentPlayer);
      System.out.println(p.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (p.isInJail()) {
            isGettingOutOfPenaltyBox = roll % 2 != 0;
         if (isGettingOutOfPenaltyBox) {

            System.out.println(p.getName() + " is getting out of the penalty box");
            executePlayerTurn(roll);

         } else {
            System.out.println(p.getName() + " is not getting out of the penalty box");
         }

      } else {
         executePlayerTurn(roll);
      }
   }

   private void executePlayerTurn(int roll) {
      movePlayer(roll);
      Player p = players.get(currentPlayer);
      System.out.println(p.getName() + "'s new location is " + p.getPosition());
      System.out.println("The category is " + currentCategory());

      askQuestion();
   }

   private void movePlayer(int roll) {
      Player p = players.get(currentPlayer);
      int pos = p.getPosition() + roll;
      if(pos > MAP_SIZE){
         pos = pos % MAP_SIZE;
      }
      p.setPosition(pos);
   }

   private void askQuestion() {
      System.out.println(categoriesQuestions.get(currentCategory()).removeFirst());
   }

   private Categorie currentCategory() {
      int playerPos = players.get(currentPlayer).getPosition();

      for (int i = 0; i < Categorie.values().length; i++) {
         if ((playerPos - i) % Categorie.values().length == 0) {
            return Categorie.values()[i];
         }
      }
      return null;
   }

   public boolean handleCorrectAnswer() {
      Player p = players.get(currentPlayer);
      if (p.isInJail() && !isGettingOutOfPenaltyBox) {
         nextPlayer();
         return true;
      }
      return correctAnswer();
   }

   private boolean correctAnswer() {
      Player p = players.get(currentPlayer);
      System.out.println("Answer was correct!!!!");

      p.increaseStreak();

      int points = p.getStreak() > 3 ? 2 : 1;
      p.setScore(p.getScore() + points);

      System.out.println(players.get(currentPlayer).getName()
              + " now has "
              + p.getScore()
              + " Gold Coins.");

      boolean win = didPlayerWin();
      nextPlayer();
      return win;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      Player p = players.get(currentPlayer);

      if(p.hasSecondChance()){
         p.setSecondChance(false);
         askQuestion();
      }
      else if(p.getStreak() == 0) {
         p.setInJail(true);
      } else {
         p.resetStreak();
      }

      System.out.println(p.getName() + " was sent to the penalty box");
      nextPlayer();
      return true;
   }

   @Override
   public boolean isGameInProgress() {
      return gameInProgress;
   }

   @Override
   public boolean isGameValid() {
      return  players.size() > 1;
   }

   private void nextPlayer() {
      currentPlayer++;
      if (currentPlayer == numberOfPlayer()) currentPlayer = 0;
   }

   private boolean didPlayerWin() {
      return players.get(currentPlayer).getScore() >= MAX_PLAYER_COUNT * 2;
   }

   public List<Player> getPlayers() {
      return new ArrayList<>(players);
   }
}
