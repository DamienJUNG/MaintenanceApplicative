package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// TODO REFACTOR ME
public class Game implements IGame {
   public static final int MAX_PLAYER_COUNT = 6;

   ArrayList<Player> players = new ArrayList<>();
   
   Map<String, LinkedList<String>> categoriesQuestions = new HashMap<String, LinkedList<String>>();

   LinkedList<String> popQuestions = new LinkedList<>();
   LinkedList<String> scienceQuestions = new LinkedList<>();
   LinkedList<String> sportsQuestions = new LinkedList<>();
   LinkedList<String> rockQuestions = new LinkedList<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      for (Categories categorie : Categories.values()) {
         categoriesQuestions.put(categorie.name(), new LinkedList<String>());
      }
      for (int i = 0; i < 50; i++) {
         for (Categories categorie : Categories.values()) {
            categoriesQuestions.get(categorie.name()).addLast(categorie.name() + " Question " + i);
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

   private String currentCategory() {
      int playerPos = players.get(currentPlayer).getPosition();
      //1 >= places[currentPlayer] <= MAX_PLAYER_COUNT
      //Pop -> (1, 5, 9) 0, 4, 8
      if ((playerPos - 1) % 4 == 0) return "Pop";
      //Sports -> (3, 7, 11) 2, 6, 10
      else if ((playerPos - 1) % 2 == 0) return "Sports";
      //Sciences -> (2, 6, 10) 0, 4, 8
      else if ((playerPos - 2) % 4 == 0) return "Science";
      //Rock -> (4, 8, 12)
      else return "Rock";
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
