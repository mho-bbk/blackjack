import blackjack.Deck;

import static org.junit.Assert.*;

public class DeckTest {

    @org.junit.Test
    public void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCards().size());
    }

    @org.junit.Test
    public void testToStringOverridden() {
        Deck deck = new Deck();
        assertEquals("[K, Q, J, A, 2, 3, 4, 5, 6, 7, 8, 9, 10, " +
                "K, Q, J, A, 2, 3, 4, 5, 6, 7, 8, 9, 10, " +
                "K, Q, J, A, 2, 3, 4, 5, 6, 7, 8, 9, 10, " +
                "K, Q, J, A, 2, 3, 4, 5, 6, 7, 8, 9, 10]", deck.toString());
    }

    @org.junit.Test
    public void testDrawIsValid() {
        Deck deck = new Deck();

        String card = deck.draw();

        //Enhanced switch is from Java 12
        boolean testResult = switch (card) {
            case "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "K", "Q", "J" -> true;
            default -> false;
        };

        assertTrue(testResult);
    }

    @org.junit.Test
    public void testCardRemoval() {
        Deck deck = new Deck();
        deck.draw();
        assertEquals(51, deck.getCards().size());
    }

}