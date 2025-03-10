package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// TODO REFACTOR ME
public class Game implements IGame {
   public static final int MAX_PLAYER_COUNT = 6;

   ArrayList<Player> players = new ArrayList<>();
   
   Map<Categorie, LinkedList<String>> categoriesQuestions = new HashMap<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      for (Categorie categorie : Categorie.values()) {
         categoriesQuestions.put(categorie, new LinkedList<>());
      }
      for (int i = 0; i < 50; i++) {
         for (Categorie categorie : Categorie.values()) {
            categoriesQuestions.get(categorie).addLast(categorie.toString() + " Question " + i);
         }
      }
   }

   public boolean add(String playerName) {
      
      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + numberOfPlayer());
      return true;
   }

   public int numberOfPlayer() {
      return players.size();
   }

   public void roll(int roll) {
      Player p = players.get(currentPlayer);
      System.out.println(p.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (p.isInJail()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(p.getName() + " is getting out of the penalty box");

            executePlayerTurn(roll);

         } else {
            isGettingOutOfPenaltyBox = false;
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
      if(pos > 12){
         pos = pos % 12;
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
      if (p.isInJail()) {
         if (isGettingOutOfPenaltyBox) {
            return correctAnswer();

         } else {
            nextPlayer();
            return true;
         }
      } else {
         return correctAnswer();
      }
   }

   private boolean correctAnswer() {
      Player p = players.get(currentPlayer);
      System.out.println("Answer was correct!!!!");
      p.setScore(p.getScore() + 1);
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
      players.get(currentPlayer).setInJail(true);
      System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
      nextPlayer();
      return true;
   }

   private void nextPlayer() {
      currentPlayer++;
      if (currentPlayer == numberOfPlayer()) currentPlayer = 0;
   }

   private boolean didPlayerWin() {
      return players.get(currentPlayer).getScore() != MAX_PLAYER_COUNT;
   }
}
