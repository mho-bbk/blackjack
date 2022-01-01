import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

    private Deck deck = new Deck();
    private static List<String> hand = new ArrayList<>();
    private int score = 0;
    int validHand = 1;
    boolean gameOver = false;

    public Blackjack() {
        //empty constructor
    }

    public void play() {
        System.out.println("Let's start the game! DEAL!");
        deal();
        //Evaluate hand here in case 21 has been dealt
        evaluateHand();

        if(validHand == 0) {
            //stop game
            System.out.println("Congratulations! You've won the game.");
        } else {
            printGame();

            Scanner scan = new Scanner(System.in);
            while(!gameOver) {
                System.out.println("Hit or Stand? Enter 'H' to hit and any other letter to Stand.");
                String userReply = scan.nextLine();
                if (userReply.equals("H") || userReply.equals("h")) {
                    hit();
                    printGame();
                } else {
                    stand();
                    printGame();
                }
            }
        }
    }

    /**
     * Players are initially dealt two cards.
     * 'Deal' is used to initiate the game.
     * After the 'deal', when a player wants a card they must 'hit'.
     */
    private void deal() {
        hand.add(deck.draw());
        hand.add(deck.draw());
        updateScore();
    }

    private void hit() {
        hand.add(deck.draw());
        updateScore();
        evaluateHand();
    }

    private void stand() {
        evaluateHand();
        System.out.println("Game finished with final score " + getScore());
        gameOver = true;
    }

    /**
     * Tests whether the hand is valid and sets the hand's validity.
     * A hand is no longer valid when the score is 22 or more.
     * A game is over when the hand is either not valid OR score of 21 has been reached.
     */
    private void evaluateHand() {
        if(score < 21) {
            //Hand is valid
            validHand = 1;
        } else if(score > 21) {
            //Game over
            System.out.println("BUST! Game over");
            validHand = -1;
            gameOver = true;
        } else {
            //Score must be 21
            System.out.println("Congratulations! You've won the game.");
            validHand = 0;
            gameOver = true;
        }
    }

    /**
     * Calculates the score of the current hand
     * @return int representing score of current hand
     */
    private int calculateScore() {
        int score = 0;
        for (String value: hand) {
            try {
                score += Integer.parseInt(value);
            } catch(NumberFormatException nfe) {
                switch(value) {
                    case "K", "Q", "J" -> score += 10;
                    //TODO - Ace can be 1 or 11 (player's choice)
                    case "A" -> score += 1;
                };
            }
        }
        return score;
    }

    private void updateScore() {
        score = calculateScore();
    }

    public int getScore() {
        return score;
    }

    public void printGame() {
        printCurrentHand();
        printCurrentDeck();
        printScore();
    }

    public void printCurrentHand() {
        System.out.println("Current Hand: " + hand.toString());
    }

    public void printCurrentDeck() {
        System.out.println("Current Deck: " + deck.toString());
    }

    public void printScore() {
        System.out.println("Current Score: " + score);
    }
}
