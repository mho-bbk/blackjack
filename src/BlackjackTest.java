import static org.junit.Assert.*;

public class BlackjackTest {

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

    //Test: that when you draw a card, the card is removed from the deck (so you cannot get a hand with eg 5 x 9s)
    @org.junit.Test
    public void testCardRemoval() {
        Deck deck = new Deck();
        deck.draw();
        assertEquals(51, deck.getCards().size());
    }

    @org.junit.Test
    public void testPlay() {
        Blackjack newGame = new Blackjack();
        newGame.play();
        newGame.printCurrentDeck();
        newGame.printCurrentHand();
    }
}