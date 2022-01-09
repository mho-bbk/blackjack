package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private final List<String> cards = new ArrayList<>();

    public Deck() {
        //Every deck should have 4 x cards 2-10, scored as numbered
        //Every deck should have 4 x aces, scored as either 1 or 11 (player's choice)
        //Every deck should have 4 x K, Q, J, scored as 10 each
        for (int i = 0; i < 4; i++) {
            cards.add("K");
            cards.add("Q");
            cards.add("J");
            cards.add("A");
            for (int j = 2; j < 11; j++) {
                cards.add(String.valueOf(j));
            }
        }
    }

    /**
     * Draws a card from the deck at random.
     * When a card is drawn, that card must be removed from the deck.
     * @return String representing the number/letter (not the suit) of the card drawn
     */
    public String draw() {
        Random numGenerator = new Random();
        //size of the cards is constantly shrinking. numGenerator must change accordingly to avoid IndexOutOfBoundException
        String card = cards.get(numGenerator.nextInt(cards.size()));
        cards.remove(card);
        return card;
    }

    /**
     * Getter method for cards in the deck.
     * @return the remaining cards in the deck (excluding those cards that have been removed)
     */
    public List<String> getCards() {
        return cards;
    }

    /**
     * Prints the deck as its cards.
     * @return a string representing the remaining cards in the deck.
     */
    @Override
    public String toString() {
        return cards.toString();
    }
}
