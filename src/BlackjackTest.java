import static org.junit.Assert.*;

public class BlackjackTest {

    @org.junit.Test
    public void testDraw() {
        Deck deck = new Deck();

        //Test: draw a random card and certify that it's a valid card in a deck
        //ie either 2-9, 'A', 'K', 'J', or 'Q'
        String card = deck.draw();

        //Enhanced switch is from Java 12
        boolean testResult = switch (card) {
            case "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "K", "Q", "J" -> true;
            default -> false;
        };

        assertTrue(testResult);
    }

    @org.junit.Test
    public void testPlay() {

    }
}