import static org.junit.Assert.*;

public class DeckTest {

    @org.junit.Test
    public void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCards().size());
    }

    @org.junit.Test
    public void testDeckContent() {
        Deck deck = new Deck();
        //tests toString method which is overridden and helps us to see cards
        System.out.println(deck);
    }

    @org.junit.Test
    public void testDraw() {
        Deck deck = new Deck();

        //Test is to certify that draw is valid
        //ie resulting String card is 2-9, 'A', 'K', 'J', or 'Q'
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