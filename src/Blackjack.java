import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blackjack {

    private Deck deck = new Deck();
    private static List<String> hand = new ArrayList<>();
    private int score = 0;

    public Blackjack() {
        //empty constructor
    }

    public void play() {
        deal();
        updateScore();

        printCurrentHand();
        printCurrentDeck();
        printScore();
    }

    /**
     * Players are initially dealt two cards.
     * 'Deal' is used to initiate the game.
     * After the 'deal', when a player wants a card they must 'hit'.
     */
    private void deal() {
        hand.add(deck.draw());
        hand.add(deck.draw());
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
