import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Deck {

    private static List<String> cards = new ArrayList<>();
//    private HashMap<Character, Integer> card_count = new HashMap<>();

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
     * @return String representing the number (not the suit) of the card drawn
     */
    public String draw() {
        Random numGenerator = new Random();
        //52 number of cards in a deck, so upper bound 9exclusive) 53
        String card = cards.get(numGenerator.nextInt(53));
        cards.remove(card);
        return card;
    }

    public List<String> getCards() {
        return cards;
    }
}
