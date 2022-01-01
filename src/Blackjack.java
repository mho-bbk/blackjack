import java.util.ArrayList;
import java.util.List;

public class Blackjack {

    private static Deck deck = new Deck();
    private static List<String> hand = new ArrayList<>();

    public Blackjack() {
        //empty constructor
    }

    public void play() {
        deal();
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

    public void printCurrentHand() {
        System.out.println("Current Hand: " + hand.toString());
    }

    public void printCurrentDeck() {
        System.out.println("Current Deck: " + deck.toString());
    }
}
