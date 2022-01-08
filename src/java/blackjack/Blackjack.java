package blackjack;

public abstract class Blackjack {

    protected final Deck deck = new Deck();
    protected boolean gameOver;

    /**
     * Applications will use this public method to initiate and play the game.
     */
    public abstract void play();

    /**
     * This method will print the game in a human readable format.
     */
    public abstract void printGame();

    /**
     * Players are initially dealt two cards.
     * After the 'deal', when a player wants a card they must 'hit'. Else, they must 'stand'.
     */
    protected void deal(Hand hand) {
        hand.addCard(deck.draw());
        hand.addCard(deck.draw());
        hand.update();
    }

    /**
     * Each hit draws one card and adds it to a player's hand.
     * @param hand of the player that a card is added to.
     */
    protected void hit(Hand hand) {
        hand.addCard(deck.draw());
        hand.update();
    }

    /**
     * Stand means a player does not want another card.
     * The player's turn ends.
     * @param hand of the player that stands.
     */
    protected void stand(Hand hand) {
        System.out.println(hand.getName() + " has chosen to stand. " + hand.getName() + "'s turn has ended.");
    }

    /**
     * Checks whether the hand remains valid or whether the game is over (21 or bust).
     * @param hand that's status will be checked.
     */
    protected void checkHandStatus(Hand hand) {
        if(hand.getStatus() == 0) {
            System.out.println("Congratulations! " + hand.getName() + " has won the game.");
            gameOver = true;
        } else if (hand.getStatus() == -1) {
            System.out.println("BUST! Game over.");
            gameOver = true;
        }
    }

}
