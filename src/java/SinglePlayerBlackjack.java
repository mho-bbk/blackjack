import java.util.Scanner;

public class SinglePlayerBlackjack {

    private final Deck deck = new Deck();
    private final Hand mainHand = new Hand();
    private boolean gameOver = false;

    public SinglePlayerBlackjack() {
        //empty constructor
    }

    public void play() {
        System.out.println("Let's start the game! DEAL!");
        deal(mainHand);

        if(!gameOver) {
            printHand();

            Scanner scan = new Scanner(System.in);
            while(!gameOver) {
                System.out.println("Hit or Stand? Enter 'H' to hit and any other letter to Stand.");
                String userReply = scan.nextLine();
                if (userReply.equals("H") || userReply.equals("h")) {
                    hit(mainHand);
                } else {
                    stand(mainHand);
                }
                printHand();
            }
        }
    }

    /**
     * Players are initially dealt two cards.
     * 'Deal' is used to initiate the game.
     * After the 'deal', when a player wants a card they must 'hit'.
     */
    private void deal(Hand hand) {
        hand.addCard(deck.draw());
        hand.addCard(deck.draw());
        hand.updateScore();

        //Evaluate hand here in case 21 has been dealt
        if(hand.evaluate() == 0) {
            System.out.println("Congratulations! You've won the game.");
            printHand();
            gameOver = true;
        } else if (hand.evaluate() == -1) {
            System.out.println("BUST! Game over");
            printHand();
            gameOver = true;
        }
    }

    private void hit(Hand hand) {
        hand.addCard(deck.draw());
        hand.updateScore();
        if(hand.evaluate() == 0) {
            System.out.println("Congratulations! You've won the game.");
            gameOver = true;
        } else if (hand.evaluate() == -1) {
            System.out.println("BUST! Game over");
            gameOver = true;
        }
    }

    private void stand(Hand hand) {
        hand.evaluate();
        System.out.println("STAND! Game finished. ");
        gameOver = true;
    }

    public void printHand() {
        mainHand.printHand();
    }
}
